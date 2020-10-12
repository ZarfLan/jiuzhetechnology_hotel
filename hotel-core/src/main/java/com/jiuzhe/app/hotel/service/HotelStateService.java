package com.jiuzhe.app.hotel.service;

import com.jiuzhe.app.hotel.dto.HotelStateDto;
import com.jiuzhe.app.hotel.module.HotelStataQuery;

/**
 * @Description:房态的service
 */
public interface HotelStateService {

    HotelStateDto getHotelState(HotelStataQuery query);
}
