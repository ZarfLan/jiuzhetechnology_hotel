package com.jiuzhe.app.hotel.control;

import com.jiuzhe.app.hotel.constants.CommonConstant;
import com.jiuzhe.app.hotel.constants.OrderStatusEnum;
import com.jiuzhe.app.hotel.dto.LatLngDto;
import com.jiuzhe.app.hotel.dto.OrderDto;
import com.jiuzhe.app.hotel.dto.OrderSuccessDto;
import com.jiuzhe.app.hotel.dto.ResponseBase;
import com.jiuzhe.app.hotel.entity.HotelOrder;
import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.module.EvaluateQuery;
import com.jiuzhe.app.hotel.module.HotelOrderQuery;
import com.jiuzhe.app.hotel.module.OrderCancelQuery;
import com.jiuzhe.app.hotel.service.HotelorderService;
import com.jiuzhe.app.hotel.service.RabbitMQService;
import com.jiuzhe.app.hotel.service.RoomReservationService;
import com.jiuzhe.app.hotel.utils.CheckUtil;
import com.jiuzhe.app.hotel.utils.StringUtil;
import com.jiuzhe.app.hotel.utils.TimeUtil;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:订单服务的controller
 */
@Api(tags = "订单服务的controller")
@RestController
@RequestMapping("/hotelorders")
public class HotelOrderController {
    @Autowired
    HotelorderService hotelorderService;
    @Autowired
    RoomReservationService roomReservationService;
    @Autowired
    RabbitMQService rabbitservice;
    private static final Logger logger = LoggerFactory.getLogger(HotelOrderController.class);
    @Value("${hotel.order.user.max-num}")
    private int orderMaxNum;

    /**
     * @Description:校验并生成订单
     */
    @ApiOperation(value = "校验并生成订单", notes = "校验并生成订单")
    @PostMapping
    public ResponseBase<OrderSuccessDto> creatHotelOrder(@RequestBody() HotelOrderQuery query) {
        String beginDt = query.getStartDate().toString();
        String endDt = query.getEndDate().toString();
        String now = LocalDate.now().toString();
        String userId = query.getUserId();
        Integer unpaidIndex = OrderStatusEnum.UNPAID.getIndex();
        ResponseBase<OrderSuccessDto> res = new ResponseBase<>();
        try {
            //对订房时间的校验（必须是今天或者以后，同时后面的日期大于前面）
            if (CheckUtil.compare_date(endDt, beginDt) || CheckUtil.compare_date(beginDt, now)) {
                res.setStatus(CommonConstant.OUT_DATA_EMPTY);
                res.setMessage("时间有问题！");
                return res;
            }
            //幂等性判定
            HotelOrder hotelOrder = hotelorderService.getOrderById(query.getId());
            if (null != hotelOrder) {
                res.setStatus(CommonConstant.EXISTED);
                return res;
            }
            //需要验证用户未付款订单数，多于2不许再订
            if (hotelorderService.getOrderNumByStatus(userId, unpaidIndex) >= orderMaxNum) {
                res.setStatus(CommonConstant.TOO_MANY_ORDER);
                res.setMessage("未已付款订单数超过最大值");
                return res;
            }
            //获取房间状态
            Map map = hotelorderService.getReservation(query.getSkuId(), beginDt, endDt);
            if ("0".equals(map.get("status"))) {
                if (!"1".equals(map.get("canBeReserved"))) {
                    res.setStatus(CommonConstant.RESERVER);
                    res.setMessage("该房间已经被预定");
                    return res;
                }
            } else {
                throw new RuntimeException();
            }
            //校验通过创建订单
            OrderSuccessDto dto = hotelorderService.creatHotelOrder(query);
            res.setStatus(CommonConstant.SUCCESS);
            res.setData(dto);
        } catch (Exception e) {
            res.setStatus(CommonConstant.FAIL);
        }
        return res;
    }

    /**
     * @Description:修改订单状态
     */
    @ApiOperation(value = "修改订单状态", notes = "只使用于付款，付款中，订单完成")
    @PostMapping("/orderchange")
    public ResponseBase changeHotelOrder(
            @ApiParam(name = "Map<id,status>", value = "id:1,status:2") @RequestBody Map<String, String> map) {
        String id = map.get("id");
        Integer status = Integer.valueOf(map.get("status"));
	System.out.println(id+"================"+status);
        ResponseBase responseBase = new ResponseBase();
        if (!StringUtil.isEmptyOrNull(id) && null != status) {
            try {
                int num = hotelorderService.changeStatus(id, status);
                if (num == 0) {
                    responseBase.setStatus(CommonConstant.ID_EMPTY);
                    responseBase.setMessage("订单号不存在！");
                } else {
                    responseBase.setStatus(CommonConstant.SUCCESS);
                }
            } catch (Exception e) {
                logger.error("orderchange failed!", e);
                logger.debug("修改订单状态异常");
                responseBase.setStatus(CommonConstant.FAIL);
                responseBase.setMessage("异常");
            }
        } else {
            responseBase.setStatus(CommonConstant.QUERY);
            responseBase.setMessage("参数错误");
        }

        return responseBase;
    }

    /**
     * @Description:取消订单
     */
    @ApiOperation(value = "取消订单", notes = "只使用取消订单")
    @PostMapping("/ordercancel")
    public ResponseBase<String> cancelOrder(@RequestBody OrderCancelQuery query) {
        ResponseBase<String> responseBase = new ResponseBase<>();
        if (null != query) {
            try {
                int num = hotelorderService.cancelOrder(query);
                if (num == CommonConstant.PAID_CANCEL_TIME) {
                    responseBase.setStatus(CommonConstant.PAID_CANCEL_TIME);
                    responseBase.setMessage("订单支付后两小时不能取消,当天支付");
                } else {
                    responseBase.setStatus(CommonConstant.SUCCESS);
                }
            } catch (Exception e) {
                logger.error("ordercancel failed!", e);
                logger.debug("取消订单异常");
                responseBase.setStatus(CommonConstant.FAIL);
            }
        } else {
            responseBase.setStatus(CommonConstant.ID_EMPTY);
            responseBase.setMessage("参数问题");
        }
        return responseBase;
    }

    /**
     * @Description:根据用户id获取订单信息
     */
    @ApiOperation(value = "根据用户id获取订单信息", notes = "根据用户id获取订单信息")
    @PostMapping("/userid")
    public ResponseBase<List<OrderDto>> getOrderbyUserId(
            @ApiParam(name = "Map<String,String>", value = "userId:1") @RequestBody Map<String, Object> map) {
        ResponseBase<List<OrderDto>> responseBase = new ResponseBase<>();
        String userId = map.get("userId").toString();
        if (!StringUtil.isEmptyOrNull(userId)) {
            try {
                List<HotelOrder> orders = hotelorderService.getOrderbyUserId(userId);

                List<OrderDto> dtos = orders.stream()
                        .map(order -> OrderDto.make(order))
                        .collect(Collectors.toList());
                responseBase.setStatus(CommonConstant.SUCCESS);
                responseBase.setData(dtos);
            } catch (Exception e) {
                logger.error("getOrderByUserId failed!", e);
                responseBase.setStatus(CommonConstant.FAIL);
                responseBase.setMessage("异常");
            }
        } else {
            responseBase.setStatus(CommonConstant.ID_EMPTY);
            responseBase.setMessage("userId为空");
        }
        return responseBase;
    }

    /**
     * @Description:根据商户ID获取订单信息
     */
    @ApiOperation(value = "根据商户ID获取订单信息", notes = "根据商户ID获取订单信息")
    @PostMapping("/merchantId")
    public ResponseBase<List<OrderDto>> getOrderbyMerchantId(
            @ApiParam(name = "Map<String,String>", value = "merchantId:1") @RequestBody Map<String, Object> map) {
        ResponseBase<List<OrderDto>> responseBase = new ResponseBase<>();
        String merchantId = map.get("merchantId").toString();
        try {
            List<HotelOrder> orders = hotelorderService.getOrderbyMerchantId(merchantId);
            List<OrderDto> dtos = orders.stream()
                    .map(order -> OrderDto.make(order))
                    .collect(Collectors.toList());
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(dtos);
        } catch (Exception e) {

            logger.error("getOrderbyMerchantId failed!", e);
            responseBase.setStatus(CommonConstant.FAIL);
            responseBase.setMessage("异常");
        }
        return responseBase;
    }

    /**
     * @Description:根据商户ID获取退押金的订单
     */
    @ApiOperation(value = "根据商户ID获取退押金的订单", notes = "根据商户ID获取退押金的订单")
    @PostMapping("/bondreorder")
    public ResponseBase<List<OrderDto>> getBondOrderbyMerchantId(@RequestBody Map<String, Object> map) {
        ResponseBase<List<OrderDto>> responseBase = new ResponseBase<>();
        String merchantId = map.get("merchantId").toString();
        try {
            List<HotelOrder> orders = hotelorderService.getBondOrderbyMerchantId(merchantId);
            List<OrderDto> dtos = orders.stream()
                    .map(order -> OrderDto.make(order))
                    .collect(Collectors.toList());
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(dtos);
        } catch (Exception e) {

            logger.error("getOrderbyMerchantId failed!", e);
            responseBase.setStatus(CommonConstant.FAIL);
            responseBase.setMessage("异常");
        }
        return responseBase;
    }

    @ApiOperation(value = "根据商户ID获取订单信息", notes = "根据商户ID获取订单信息")
    @PostMapping("/applyorder")
    public ResponseBase<List<OrderDto>> getApplyOrder(
            @ApiParam(name = "Map<String,String>", value = "merchantId:1") @RequestBody Map<String, Object> map) {
        ResponseBase<List<OrderDto>> responseBase = new ResponseBase<>();
        String merchantId = map.get("merchantId").toString();
        Integer currentPage = Integer.parseInt(map.get("currentPage").toString());
        Integer number = Integer.parseInt(map.get("number").toString());
        try {
            List<HotelOrder> orders = hotelorderService.getApplyOrderbyMerchantId(merchantId, currentPage, number);
            List<OrderDto> dtos = orders.stream()
                    .map(order -> OrderDto.make(order))
                    .collect(Collectors.toList());
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(dtos);
        } catch (Exception e) {
            responseBase.setStatus(CommonConstant.FAIL);
            responseBase.setMessage("异常");
        }
        return responseBase;
    }

    /**
     * @Description:通过订单id获取订单
     */
    @ApiOperation(value = "通过订单id获取订单信息", notes = "通过订单id获取订单信息")
    @PostMapping("/id")
    public ResponseBase<OrderDto> getOrderbyId(
            @ApiParam(name = "Map<String,String>", value = "id:1") @RequestBody Map<String, String> map) {
        ResponseBase<OrderDto> responseBase = new ResponseBase<>();
        String id = map.get("id");
        if (StringUtil.isEmptyOrNull(id)) {
            responseBase.setStatus(CommonConstant.ID_EMPTY);
            responseBase.setMessage("id为空");
            return responseBase;
        }
        try {
            HotelOrder order = hotelorderService.getOrderById(id);
            OrderDto dto = OrderDto.make(order);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(dto);
        } catch (Exception e) {
            logger.error("getOrderById failed!", e);
            responseBase.setStatus(CommonConstant.FAIL);
            responseBase.setMessage("异常");
        }
        return responseBase;
    }

    /**
     * @Description:获取订单倒计时
     */
    @ApiOperation(value = "获取倒计时", notes = "获取倒计时")
    @GetMapping("/countdown")
    public ResponseBase<Map<String, Integer>> getCountDown(@RequestParam(required = true) String id) {
        ResponseBase<Map<String, Integer>> responseBase = new ResponseBase<>();
        try {
            Integer second = hotelorderService.getCountDown(id);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(new HashMap<String, Integer>() {{
                put("countdown", second);
            }});
        } catch (Exception e) {
            logger.error("countdown failed!", e);
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }

    /**
     * @Description:获取经纬度
     */
    @ApiOperation(value = "获取经纬度", notes = "获取经纬度")
    @PostMapping("/lnglat")
    public ResponseBase<LatLngDto> getLngLat(
            @ApiParam(name = "Map<String,String>", value = "skuId:1") @RequestBody Map<String, String> map) {
        String skuId = map.get("skuId");
        ResponseBase<LatLngDto> responseBase = new ResponseBase<>();
        try {
            HotelSku sku = hotelorderService.getLngLatBySkuId(skuId);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(LatLngDto.make(sku));
        } catch (Exception e) {
            logger.error("lnglat failed!", e);
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }


    @ApiOperation(value = "用户评价", notes = "用户评价")
    @PostMapping("/score")
    public ResponseBase saveOrderScore(@RequestBody Map<String, String> map) {
        ResponseBase responseBase = new ResponseBase<>();
        String orderId = map.get("orderId");
        String skuId = map.get("skuId");
        Integer score = Integer.valueOf(map.get("score"));

        try {
            hotelorderService.saveOrderScore(skuId, orderId, score);
            responseBase.setStatus(CommonConstant.SUCCESS);
        } catch (Exception e) {
            responseBase.setStatus(CommonConstant.FAIL);
            logger.debug("用户评价失败！");
        }
        return responseBase;
    }

    /**
     * @Description:用户评价
     */
    @PostMapping("/evaluate")
    public ResponseBase evaluate(@RequestBody EvaluateQuery query) {
        ResponseBase responseBase = new ResponseBase();
        try {
            hotelorderService.evaluate(query);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(query);
        } catch (Exception e) {
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }
}
