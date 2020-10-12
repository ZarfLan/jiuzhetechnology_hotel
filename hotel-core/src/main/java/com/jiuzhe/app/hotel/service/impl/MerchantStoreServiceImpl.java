package com.jiuzhe.app.hotel.service.impl;

import com.jiuzhe.app.hotel.service.MerchantStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.jiuzhe.app.hotel.constants.rtCodeConstant;
import com.jiuzhe.app.hotel.dao.StoreDao;
import com.jiuzhe.app.hotel.utils.MD5Util;


import java.util.List;
import java.util.Map;

@Service
public class MerchantStoreServiceImpl implements MerchantStoreService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
    RestTemplate restTemplate;

	public Map createStore(StoreDao store) {
		String mid = store.getMerchant_id();
		String name = store.getName();
		String city = store.getCity();
		String phone = store.getPhone();
		String phone1 = store.getPhone1();
		String phone2 = store.getPhone2();
		String storePic = store.getStorePic();
		String receptionPic = store.getReceptionPic();
		int lng = store.getLng();
    	int lat = store.getLat();
    	String address = store.getAddress();
    	String storeId = MD5Util.MD5(mid + name);

		jdbcTemplate.update(String.format("insert into store(id,merchant_id,name,city,phone,phone1,phone2,lng,lat,address,storePic,ReceptionPic) values('%s','%s','%s','%s','%s','%s','%s',%d,%d,'%s','%s','%s')",storeId,mid,name,city,phone,phone1,phone2,lng,lat,address,storePic,receptionPic));
		return rtCodeConstant.getResult("0",storeId);
	}

	public Map queryAllStore(String id) {
		List stores = jdbcTemplate.queryForList(String.format("select id,name,city,phone,phone1,phone2,lng,lat,address ,storePic,ReceptionPic receptionPic from store where merchant_id = '%s' order by create_dt", id));
		if (stores.size() == 0) {
			String url = "http://jzt-platform-core/hr/getstoreidsbyhrid/" + id;
			String response = restTemplate.getForObject(url, String.class);
			if (response == null)
				return rtCodeConstant.getResult("0");

			if (response.equals("-1")) {
				return rtCodeConstant.getResult("-1");
			}

			stores = jdbcTemplate.queryForList(String.format("select id,name,city,phone,phone1,phone2,lng,lat,address,storePic,ReceptionPic receptionPic from store where id in %s order by create_dt", response));
		}
		return rtCodeConstant.getResult("0", stores);
	}

	public Map querySingleStore(String id) {
		List stores = jdbcTemplate.queryForList(String.format("select merchant_id,name,city,phone,phone1,phone2,lng,lat,address ,storePic,ReceptionPic receptionPic from store where id = '%s'", id));
		return rtCodeConstant.getResult("0", stores);
	}

	public Map updateSingleStore(String argid,StoreDao store) {
		String name = store.getName();
		String city = store.getCity();
		String phone = store.getPhone();
		String phone1 = store.getPhone1();
		String phone2 = store.getPhone2();
		String storePic = store.getStorePic();
		String receptionPic = store.getReceptionPic();

		int lng = store.getLng();
    	int lat = store.getLat();
    	String address = store.getAddress();

		jdbcTemplate.update(String.format("update store set name = '%s',city = '%s',phone = '%s',phone1 = '%s',phone2 = '%s',lng = %d,lat = %d,address = '%s', storePic = '%s',ReceptionPic = '%s' ,updt = now() where id = '%s'", name,city,phone,phone1,phone2,lng,lat,address,storePic,receptionPic,argid));
		return rtCodeConstant.getResult("0");
	}

	@Transactional
	public Map removeStore(String id) {
		jdbcTemplate.update(String.format("delete from store where id = '%s'", id));
		jdbcTemplate.update(String.format("delete from hotel_sku_layout where store_id = '%s'", id));
		jdbcTemplate.update(String.format("delete from hotel_sku where store_id = '%s'", id));
		return rtCodeConstant.getResult("0");
	}
   
}
