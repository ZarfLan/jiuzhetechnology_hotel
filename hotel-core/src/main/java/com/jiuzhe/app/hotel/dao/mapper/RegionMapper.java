package com.jiuzhe.app.hotel.dao.mapper;

import com.jiuzhe.app.hotel.entity.HotelRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RegionMapper {

    @Select("SELECT id,city_name cityName,areas FROM hotel_region ")
    List<HotelRegion> getAllRegion();

}
