package com.jiuzhe.app.hotel.entity;

import com.jiuzhe.app.hotel.utils.StringUtil;

import java.time.LocalDateTime;
/**
	 * @Description:基类，有需要的可以继承
	 */
public class BaseEntity {
    /**
     * 主键ID
     */
    private String id = StringUtil.get32UUID();
    /**
     * 版本号默认为0
     */
    private Integer version = 0;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    private LocalDateTime createTime = LocalDateTime.now();
    /**
     * 修改人
     */
    private String editor;
    /**
     * 修改时间
     */
    private LocalDateTime editTime = LocalDateTime.now();
    /**
     * 描述
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
