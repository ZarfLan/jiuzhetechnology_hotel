package com.jiuzhe.app.hotel.service.aliyun.impl;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.jiuzhe.app.hotel.service.aliyun.MnsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MnsServiceImpl implements MnsService {
    private static final Logger logger = LoggerFactory.getLogger(MnsServiceImpl.class);

    @Value("${aliyun.mns.queue-name}")
    private String queueName;

    @Autowired
    MNSClient client;

    @Override
    public void sendMessage(String msgBody) throws Exception {
        try{
            CloudQueue queue = client.getQueueRef(queueName);

            Message message = new Message();
            message.setMessageBody(msgBody);
            //message.setPriority(8);
            Message putMsg = queue.putMessage(message);
            logger.debug("Send message id is: " + putMsg.getMessageId()
                    + ". To queue is: " + queueName + ". Message is: " + msgBody);
        } catch (ClientException ce)
        {
            logger.error("Something wrong with the network connection between client and MNS service."
                    + "Please check your network and DNS availablity.");
            logger.error(ce.getStackTrace().toString());
        } catch (ServiceException se)
        {
            logger.error(se.getStackTrace().toString());
            logger.error("MNS exception requestId:" + se.getRequestId(), se);
            if (se.getErrorCode() != null) {
                if (se.getErrorCode().equals("QueueNotExist"))
                {
                    logger.error("Queue is not exist.Please create before use");
                } else if (se.getErrorCode().equals("TimeExpired"))
                {
                    logger.error("The request is time expired. Please check your local machine timeclock");
                }
            /*
            you can get more MNS service error code from following link:
            https://help.aliyun.com/document_detail/mns/api_reference/error_code/error_code.html
            */
            }
        } catch (Exception e)
        {
            logger.error("Unknown exception happened!");
            logger.error(e.getStackTrace().toString());
        }

    }
}
