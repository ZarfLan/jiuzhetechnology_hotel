package com.jiuzhe.app.hotel.control;

import com.jiuzhe.app.hotel.constants.rtCodeConstant;
import com.jiuzhe.app.hotel.module.CleanQuery;
import com.jiuzhe.app.hotel.service.CleanService;
import com.jiuzhe.app.hotel.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:保洁管理
 */
@RestController
@RequestMapping("/merchant")
public class CleanController {
    private static final Logger logger = LoggerFactory.getLogger(CleanController.class);

    @Autowired
    CleanService service;

    @RequestMapping(value = "/getcleaninfo", method = RequestMethod.POST)
    @ResponseBody
    public Map getCleanInfo(@RequestBody Map<String, List<String>> storeIdsMap) {
        try {
            List<String> storeIds = storeIdsMap.get("storeIds");
            return service.getCleanInfo(storeIds);
        } catch (DuplicateKeyException e) {
            return rtCodeConstant.getResult("2");
        } catch (Exception e) {
            logger.error(e.toString());
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/cleanup", method = RequestMethod.POST)
    @ResponseBody
    public Map cleanUp(@RequestBody CleanQuery query) {
        try {
            return service.changRoomStaus(query);
        } catch (DuplicateKeyException e) {
            return rtCodeConstant.getResult("2");
        } catch (Exception e) {
            logger.error(e.toString());
            return rtCodeConstant.getResult("-1");
        }
    }
}
