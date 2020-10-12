package com.jiuzhe.app.hotel.service.impl;

import com.jiuzhe.app.hotel.service.SkuLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.jiuzhe.app.hotel.constants.rtCodeConstant;
import com.jiuzhe.app.hotel.dao.SkuLayoutDao;
import com.jiuzhe.app.hotel.utils.StringUtil;
import com.jiuzhe.app.hotel.utils.MD5Util;

import java.util.List;
import java.util.Map;

@Service
public class SkuLayoutServiceImpl implements SkuLayoutService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map createSkuLayout(SkuLayoutDao layout) {
		String storeId = layout.getStore_id();
		String name = layout.getName();
		String piclist = StringUtil.strArray2str(layout.getPiclist());
		
		jdbcTemplate.update(String.format("insert into hotel_sku_layout(id,store_id,name,piclist) values('%s','%s','%s','%s')",MD5Util.MD5(storeId + name),storeId,name,piclist));
		return rtCodeConstant.getResult("0");
	}

	public Map queryAllSkuLayout(String id) {
		List layouts = jdbcTemplate.queryForList(String.format("select id,name,piclist,wifi,bedroom,bed,toilet,count from hotel_sku_layout where store_id = '%s'", id));
		return rtCodeConstant.getResult("0", layouts);
	}

	public Map querySingleSkuLayout(String id) {
		List layouts = jdbcTemplate.queryForList(String.format("select store_id,name,piclist,wifi,bedroom,bed,toilet,count from hotel_sku_layout where id = '%s'", id));
		return rtCodeConstant.getResult("0", layouts);
	}

	public Map updateSingleSkuLayout(String argid,SkuLayoutDao layout) {
		String name = layout.getName();
		String piclist = StringUtil.strArray2str(layout.getPiclist());
		// int wifi = layout.getWifi();
	 //    int bedroom = layout.getBedroom();
	 //    int bed = layout.getBed();
	 //    int toilet = layout.getToilet();
		// ,wifi = %d,bedroom = %d,bed = %d,toilet = %d
		// ,wifi,bedroom,bed,toilet

		jdbcTemplate.update(String.format("update hotel_sku_layout set name = '%s',piclist = '%s',updt = now() where id = '%s'", name,piclist,argid));
		return rtCodeConstant.getResult("0");
	}

	@Transactional
	public Map removeSkuLayout(String id) {
		jdbcTemplate.update(String.format("delete from hotel_sku_layout where id = '%s'", id));
		jdbcTemplate.update(String.format("delete from hotel_sku where layout_id = '%s'", id));
		return rtCodeConstant.getResult("0");
	}
   
}
