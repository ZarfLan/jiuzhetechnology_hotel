package com.jiuzhe.app.hotel.service;

import com.jiuzhe.app.hotel.entity.Version;

public interface VersionService {
    Version getLatestVersion(int devType);
}
