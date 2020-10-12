package com.jiuzhe.app.hotel.service.impl;

import com.jiuzhe.app.hotel.constants.DevTypeEnum;
import com.jiuzhe.app.hotel.dao.VersionDao;
import com.jiuzhe.app.hotel.entity.Version;
import com.jiuzhe.app.hotel.service.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @name: VersionService
 * @author: lucifinier
 * @date: 2018/5/9 09:47
 * @description: TODO
 */
@Service
public class VersionServiceImpl implements VersionService {
    private static final Logger logger = LoggerFactory.getLogger(VersionServiceImpl.class);

    @Autowired
    private VersionDao versionDao;

    @Override
    public Version getLatestVersion(int devType) {
        if (devType >= DevTypeEnum.NULL.getIndex()) {
            logger.error("dev type is err: " + devType);

            return null;
        }

        return versionDao.getLatestVersion(devType);
    }
}
