package com.jiuzhe.app.hotel.dao;

import com.jiuzhe.app.hotel.dao.mapper.VersionMapper;
import com.jiuzhe.app.hotel.entity.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VersionDao {
    @Autowired
    private VersionMapper mapper;

    public Version getLatestVersion(int devType) {
        return mapper.getLatestVersion(devType);
    }
}



