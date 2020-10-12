package com.jiuzhe.app.hotel.control;

import com.jiuzhe.app.hotel.constants.CommonConstant;
import com.jiuzhe.app.hotel.dto.FeedbackDto;
import com.jiuzhe.app.hotel.dto.OrderScoreDto;
import com.jiuzhe.app.hotel.dto.ResponseBase;
import com.jiuzhe.app.hotel.service.FeedbackService;
import io.swagger.annotations.ApiParam;
import org.bouncycastle.crypto.tls.MACAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description:用户反馈control
 */
@RestController
@RequestMapping("/userfeedback")
public class FeedbackControl {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseBase<Map<String, String>> saveUserFeedback(
            @ApiParam(name = "feedback", value = "反馈数据") @RequestBody() FeedbackDto dto
    ) {
        ResponseBase<Map<String, String>> res = new ResponseBase<>();

        int result = feedbackService.saveFeedback(dto);
        res.setStatus(result);

        if (CommonConstant.SUCCESS != result) {
            res.setMessage(CommonConstant.FAILED);
        }

        return res;
    }

    /**
     * @Description:获取门店下面的订单评分
     * @author: 郑鹏宇
     * @date 2018/8/29/029
     */
    @PostMapping("/orderscore")
    public ResponseBase getOrderScoreByStoreId(@RequestBody Map<String, String> map) {
        ResponseBase responseBase = new ResponseBase();
        String storeId = map.get("storeId");
        try {
            List<OrderScoreDto> dtos = feedbackService.getOrderScoreByStoreId(storeId);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(dtos);
        } catch (Exception e) {
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }

    /**
     * @Description:获取房间评分
     * @author: 郑鹏宇
     * @date 2018/8/29/029
     */
    @PostMapping("/skuscore")
    public ResponseBase getSkuScoreByStoreId(@RequestBody Map<String, String> map) {
        ResponseBase responseBase = new ResponseBase();
        String storeId = map.get("storeId");
        try {
            List<OrderScoreDto> dtos = feedbackService.getSkuScoreByStoreId(storeId);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(dtos);
        } catch (Exception e) {
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }
}
