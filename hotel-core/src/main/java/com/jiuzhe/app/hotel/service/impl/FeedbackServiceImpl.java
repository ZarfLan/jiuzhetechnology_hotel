package com.jiuzhe.app.hotel.service.impl;

import com.jiuzhe.app.hotel.constants.CommonConstant;
import com.jiuzhe.app.hotel.dao.FeedbackDao;
import com.jiuzhe.app.hotel.dto.FeedbackDto;
import com.jiuzhe.app.hotel.dto.OrderScoreDto;
import com.jiuzhe.app.hotel.entity.Feedback;
import com.jiuzhe.app.hotel.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:意见反馈
 * @author:张磊
 * @date:2018/4/13
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public int saveFeedback(FeedbackDto dto) {
        try {
            Feedback feedback = new Feedback();
            feedback.setUser_id(dto.getId());
            feedback.setContent(dto.getContent());

            feedbackDao.saveFeedback(feedback);
        } catch (Exception e) {
            logger.error("save feedback fail: " + dto);
            logger.error(e.getStackTrace().toString());
            return CommonConstant.FAIL;
        }
        return CommonConstant.SUCCESS;
    }

    @Override
    public List<OrderScoreDto> getOrderScoreByStoreId(String storeId) {
        return feedbackDao.getOrderScoreByStoreId(storeId);
    }

    @Override
    public List<OrderScoreDto> getSkuScoreByStoreId(String storeId) {
        return feedbackDao.getSkuScoreByStoreId(storeId);
    }
}
