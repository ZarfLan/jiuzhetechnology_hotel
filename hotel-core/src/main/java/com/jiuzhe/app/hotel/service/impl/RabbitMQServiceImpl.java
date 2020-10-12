package com.jiuzhe.app.hotel.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.AmqpConnectException;

import com.jiuzhe.app.hotel.service.RabbitMQService;
import com.jiuzhe.app.hotel.utils.StringUtil;
import com.jiuzhe.app.hotel.constants.rtCodeConstant;

import java.util.List;
import java.util.Map;


@Service
public class RabbitMQServiceImpl implements RabbitMQService,RabbitTemplate.ConfirmCallback {

    private static Log logger = LogFactory.getLog(RabbitMQServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.default}")
    private String exchange;

    @Value("${rabbitmq.queue.delay15min}")
    private String queue;

    public Map sendDelay15Min(String msg) {
        if (StringUtil.isEmptyOrNull(msg))
            return rtCodeConstant.getResult("1", "消息队列msg为空");

        MessageProperties messageproperties = new MessageProperties();
        messageproperties.setDeliveryMode(MessageDeliveryMode.fromInt(2));
        Message m = new Message(msg.getBytes(), messageproperties);
        try {
            rabbitTemplate.send(exchange, queue, m);
        }  catch (Exception e) {
            logger.info(e);
            return rtCodeConstant.getResult("5");
        }

        return rtCodeConstant.getResult("0");
    }

    public Map sendDelay15Min(String userId, String skuId, String beginDt, String endDt) {
        if (StringUtil.isEmptyOrNull(userId))
            return rtCodeConstant.getResult("1", "消息队列userId为空");

        if (StringUtil.isEmptyOrNull(skuId))
            return rtCodeConstant.getResult("1", "消息队列skuId为空");

        if (StringUtil.isEmptyOrNull(beginDt))
            return rtCodeConstant.getResult("1", "消息队列beginDt为空");

        if (StringUtil.isEmptyOrNull(endDt))
            return rtCodeConstant.getResult("1", "消息队列endDt为空");

        String msg = userId + "," + skuId + "," + beginDt + "," + endDt;
        return sendDelay15Min(msg);

    }

    public Map sendDelay15Min(String userId, String skuId, String beginDt) {
        if (StringUtil.isEmptyOrNull(userId))
            return rtCodeConstant.getResult("1", "消息队列userId为空");

        if (StringUtil.isEmptyOrNull(skuId))
            return rtCodeConstant.getResult("1", "消息队列skuId为空");

        if (StringUtil.isEmptyOrNull(beginDt))
            return rtCodeConstant.getResult("1", "消息队列beginDt为空");

        String msg = userId + "," + skuId + "," + beginDt;
        return sendDelay15Min(msg);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        return;
        // logger.info("confirm: " + correlationData.getId());
    }

}
