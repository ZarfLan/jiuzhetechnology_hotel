package com.jiuzhe.app.hotel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:用户反馈模型
 */
@ApiModel(value="用户反馈",description="用户反馈模型DTO")
public class FeedbackDto {
    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id",example="87d9fe4238f74d84be15d62984fdfe65")
    private String id;

    /**
     * 反馈内容
     */
    @ApiModelProperty(value="反馈内容",example="欢欢大哥太帅气了!")
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
