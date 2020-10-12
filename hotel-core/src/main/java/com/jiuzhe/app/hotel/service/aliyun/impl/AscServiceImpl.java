package com.jiuzhe.app.hotel.service.aliyun.impl;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.jiuzhe.app.hotel.service.aliyun.AcsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:阿里短信服务
 * @author:张磊
 * @date:2018/4/19
 */
@Service
public class AscServiceImpl implements AcsService {
    private static final Logger logger = LoggerFactory.getLogger(AscServiceImpl.class);
    /**
     * 短信服务类
     */
    @Autowired
    IAcsClient acsClient;

    /**
     * @Description:发送短信验证码
     * @author:张磊
     * @date:2018/4/19
     */
    @Override
    public boolean sendValidateCode(String phone, String name, String id) {
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();

        //使用post提交
        request.setMethod(MethodType.POST);

        //必填:待发送手机号
        request.setPhoneNumbers(phone);

        //必填:短信签名
        request.setSignName("玖折科技");

        //必填:短信模板
        request.setTemplateCode("SMS_133962281");

        //可选:模板中的变量替换JSON串
        request.setTemplateParam("{\"name\":\"" + name + "\"}");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(id);

        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            logger.error("send validate code to phone(" + phone + "failed.");
            logger.error(e.getStackTrace().toString());
            return false;
        }

        //请求成功
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            logger.debug("send validate code to phone(" + phone + "success to " + id);
            return true;
        } else {
            logger.debug("send validate code to phone(" + phone + "fail to " + id);
            return false;
        }

    }
}
