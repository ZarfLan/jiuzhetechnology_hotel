package com.jiuzhe.app.hotel.control;

import com.jiuzhe.app.hotel.constants.rtCodeConstant;
import com.jiuzhe.app.hotel.utils.SqlUtil;
import com.jiuzhe.app.hotel.dao.SkuLayoutDao;
import com.jiuzhe.app.hotel.service.RabbitMQService;
import com.jiuzhe.app.hotel.service.RoomReservationService;
import com.jiuzhe.app.hotel.service.SkuLayoutService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.Valid;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/merchant")
public class SkuLayoutController {
    private static Log logger = LogFactory.getLog(SkuLayoutController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StringRedisTemplate rt;

    @Autowired
    SkuLayoutService service;

    @Autowired
    RabbitMQService rabbitservice;

    @Autowired
    RoomReservationService roomReservationService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        // try {
            SqlUtil su = new SqlUtil(jdbcTemplate);
            List<Map<String,String>> rs = su.select().table("store").column("merchant_id","updt","phone").query();
            if (rs != null) {
                logger.info(rs);
            }
            int count = -1;

            // su.reset();
            // count = su.insert()
            // .column("id","name","merchant_id")
            // .value("xw","test","test").modify();
            // logger.info(count);
           

            su.reset();
            count = su.update()
            .column("name")
            .value("test")
            .condition("name = ", "xw").modify();
            logger.info(count);

            su.reset();
            count = su.delete().condition("name = ", "test").modify();
            logger.info(count);

             return "0k";


            // logger.info(rt.opsForValue().get("wei"));
            // rt.opsForValue().set("wei","wei");
            // return rabbitservice.sendDelay15Min("pcKoEOv367K8TG1e87Cx8a5GJH8UPdun", "e59672c67bb244ddabf077c45d31246f", "2018-08-12", "2018-08-13");
        // } catch (Exception e) {
            // logger.error(e);
            // return rtCodeConstant.getResult("-1");
            // return "ok";
        // }
    }

    @RequestMapping(value = "/test/{skuid}/{dt1}/{dt2}", method = RequestMethod.GET)
    @ResponseBody
    public Map test1(@PathVariable String skuid, @PathVariable String dt1, @PathVariable String dt2) {
        try {
            return roomReservationService.getReservation(skuid, dt1, dt2);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/test/{skuid}/{dt1}", method = RequestMethod.GET)
    @ResponseBody
    public Map test2(@PathVariable String skuid, @PathVariable String dt1) {
        try {
            return roomReservationService.getReservation(skuid, dt1);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/test1/{skuid}/{dt1}", method = RequestMethod.GET)
    @ResponseBody
    public Map test3(@PathVariable String skuid, @PathVariable String dt1) {
        try {
            return roomReservationService.setReservation(skuid, dt1);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/test1/{skuid}/{dt1}/{dt2}", method = RequestMethod.GET)
    @ResponseBody
    public Map test4(@PathVariable String skuid, @PathVariable String dt1, @PathVariable String dt2) {
        try {
            return roomReservationService.setReservation(skuid, dt1, dt2);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/skulayout", method = RequestMethod.POST)
    @ResponseBody
    public Map create(@RequestBody @Valid SkuLayoutDao layout) {
        try {
            return service.createSkuLayout(layout);
        } catch (DuplicateKeyException e) {
            return rtCodeConstant.getResult("2");
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/skulayouts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map getlayouts(@PathVariable String id) {
        try {
            return service.queryAllSkuLayout(id);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/skulayout/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map getlayout(@PathVariable String id) {
        try {
            return service.querySingleSkuLayout(id);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/skulayout/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map updatelayout(@PathVariable String id, @RequestBody @Valid SkuLayoutDao layout) {
        try {
            return service.updateSingleSkuLayout(id, layout);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/skulayout/d/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map removelayout(@PathVariable String id) {
        try {
            return service.removeSkuLayout(id);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }
}
