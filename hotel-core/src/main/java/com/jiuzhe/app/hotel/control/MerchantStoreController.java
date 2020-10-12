package com.jiuzhe.app.hotel.control;

import com.jiuzhe.app.hotel.constants.rtCodeConstant;
import com.jiuzhe.app.hotel.dao.StoreDao;
import com.jiuzhe.app.hotel.service.MerchantStoreService;
import com.jiuzhe.app.hotel.utils.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/merchant")
public class MerchantStoreController {
    private static Log logger = LogFactory.getLog(MerchantStoreController.class);

    @Autowired
    MerchantStoreService service;

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    @ResponseBody
    public Map createstore(@RequestBody @Valid StoreDao store) {
        try {
            return service.createStore(store);
        } catch (DuplicateKeyException e) {
            return rtCodeConstant.getResult("2");
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/stores/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map getstores(@PathVariable String id) {
        try {
            return service.queryAllStore(id);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }


    }

    @RequestMapping(value = "/store/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map getstore(@PathVariable String id) {
        try {
            return service.querySingleStore(id);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/store/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map updatestore(@PathVariable String id, @RequestBody @Valid StoreDao store) {
        try {
            return service.updateSingleStore(id, store);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }

    @RequestMapping(value = "/store/d", method = RequestMethod.POST)
    @ResponseBody
    public Map removestore(@RequestBody Map param) {
        try {
            String storeId = param.get("storeId").toString();
            if (StringUtil.isEmptyOrNull(storeId))
                return rtCodeConstant.getResult("1", "storeId");

            return service.removeStore(storeId);
        } catch (Exception e) {
            logger.error(e);
            return rtCodeConstant.getResult("-1");
        }
    }
}
