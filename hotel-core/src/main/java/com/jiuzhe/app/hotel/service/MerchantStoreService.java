package com.jiuzhe.app.hotel.service;

import com.jiuzhe.app.hotel.dao.StoreDao;
import java.util.Map;

public interface MerchantStoreService {
    public Map createStore(StoreDao store);
    public Map queryAllStore(String id);
    public Map querySingleStore(String id);
    public Map updateSingleStore(String id, StoreDao store);
    public Map removeStore(String id);
}
