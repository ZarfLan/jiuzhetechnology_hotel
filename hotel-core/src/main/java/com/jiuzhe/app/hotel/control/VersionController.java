package com.jiuzhe.app.hotel.control;
import com.jiuzhe.app.hotel.constants.CommonConstant;
import com.jiuzhe.app.hotel.dto.ResponseBase;
import com.jiuzhe.app.hotel.entity.Version;
import com.jiuzhe.app.hotel.service.VersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="版本查询的controller")
@RestController
@RequestMapping("/version/user")
public class VersionController {
    private static final Logger logger = LoggerFactory.getLogger(VersionController.class);

    @Autowired
    VersionService versionService;

    @GetMapping
    public ResponseBase<Version> getDevType(
            @ApiParam(name = "type", value = "设备类型:1-IOS;2-安卓") @RequestParam("type") Integer devType
    ) {
        ResponseBase<Version> responseBase = new ResponseBase<>();

        try {
            Version version = versionService.getLatestVersion(devType);

            if (null != version) {
                responseBase.setStatus(CommonConstant.SUCCESS);
                responseBase.setData(version);

            } else  {
                logger.error("get latest version failed: " + devType);

                responseBase.setStatus(CommonConstant.FAIL);
                responseBase.setMessage("获取最新版本失败");
            }
        } catch (Exception e) {
            logger.error("get version exception", e);
            responseBase.setStatus(CommonConstant.FAIL);
            responseBase.setMessage("获取最新版本失败");
        }

        return responseBase;
    }
}
