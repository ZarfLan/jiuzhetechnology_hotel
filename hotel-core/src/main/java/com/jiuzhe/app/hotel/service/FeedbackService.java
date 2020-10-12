package com.jiuzhe.app.hotel.service;

import com.jiuzhe.app.hotel.dto.FeedbackDto;
import com.jiuzhe.app.hotel.dto.OrderScoreDto;

import java.util.List;

public interface FeedbackService {
    int saveFeedback(FeedbackDto dto);

    List<OrderScoreDto> getOrderScoreByStoreId(String storeId);

    List<OrderScoreDto> getSkuScoreByStoreId(String storeId);
}
