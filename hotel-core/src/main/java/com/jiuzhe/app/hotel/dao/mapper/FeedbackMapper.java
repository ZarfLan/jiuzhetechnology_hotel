package com.jiuzhe.app.hotel.dao.mapper;

import com.jiuzhe.app.hotel.dto.OrderScoreDto;
import com.jiuzhe.app.hotel.entity.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FeedbackMapper {
    @Insert("INSERT INTO user_feedback (user_id,content) VALUES (#{user_id},#{content})")
    void saveFeedback(Feedback feedback);

    @Select("SELECT a.id orderId,a.sku_price price,s.name skuName,b.address,\n" +
            "b.room_no roomNo,substring_index(c.piclist,',',1) pic,b.store_id,\n" +
            "a.start_date startDate,a.end_date endDate,c.`name` layoutName,a.score,\n" +
            "a.sku_score skuScore,a.clean_score cleanScore,a.sku_problem skuProblem,a.clean_problem cleanProblem,\n" +
            "a.occupant_name name,a.occupant_phone phone, a.occupant_id_card occupant_id_card\n" +
            "FROM hotel_order a\n" +
            "LEFT JOIN hotel_sku b ON a.sku_id = b.id\n" +
            "LEFT JOIN hotel_sku_layout c ON b.layout_id = c.id\n" +
            "LEFT JOIN store s ON s.id = c.store_id\n" +
            "WHERE b.store_id = #{storeId}\n" +
            "ORDER BY a.create_time")
    List<OrderScoreDto> getOrderScoreByStoreId(String storeId);

    @Select("SELECT a.sku_name skuName,a.room_no roomNo,a.room_price price,\n" +
            "a.score skuScore,b.id layoutId,substring_index(b.piclist,',',1) pic,\n" +
            "b.name layoutName,a.address,a.clean_score cleanScore FROM hotel_sku a\n" +
            "LEFT JOIN hotel_sku_layout b ON a.layout_id = b.id\n" +
            "WHERE a.store_id = #{storeId}")
    List<OrderScoreDto> getSkuScoreByStoreId(String storeId);
}
