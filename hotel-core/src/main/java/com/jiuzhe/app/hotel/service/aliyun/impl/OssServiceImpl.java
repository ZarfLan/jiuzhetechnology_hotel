package com.jiuzhe.app.hotel.service.aliyun.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.jiuzhe.app.hotel.service.aliyun.OssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class OssServiceImpl implements OssService {
    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.end-point}")
    private String endpoint;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;


    @Override
    public void updateObject(String bucketName, String objKey, InputStream inputStream) {
        // 创建OSSClient实例
        // 工程中可以有多个OSSClient，也可以只有一个OSSClient。
        // OSSClient可以并发使用。
        OSSClient ossClient = new OSSClient(this.endpoint, this.accessKeyId, this.accessKeySecret);

        // 上传文件流
        PutObjectResult result = ossClient.putObject(bucketName, objKey, inputStream);

        // 关闭client
        ossClient.shutdown();
    }

    @Override
    public void deleteHotelImg(List<String> keys) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Override
    public List<String> getAllHotelImg() {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 构造ListObjectsRequest请求。
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
        // 列出文件。
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);
        List<String> imgs = new ArrayList<>();
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            System.out.println(objectSummary.getKey());
            imgs.add(objectSummary.getKey());
        }
        return imgs;
    }
}
