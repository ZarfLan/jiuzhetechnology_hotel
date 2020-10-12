package com.jiuzhe.app.hotel.module;

import com.aliyun.opensearch.sdk.generated.search.Order;

public class OpenSearchQuery {
    /**
     * 服务器名称
     */
    private String appName;
    /**
     * 起始位置
     */
    private int start;
    /**
     * 返回结果数目
     */
    private int hits;
    /**
     * 查询语句
     */
    private String query;
    /**
     * 过滤条件
     */
    private String filter;
    /**
     * 排序字段
     */
    private String sortFiled;
    /**
     * 排序规则
     */
    private Order sortType;

    public OpenSearchQuery() {
    }

    public OpenSearchQuery(String appName, int start, int hits, String query, String filter, String sortFiled, Order sortType) {
        this.appName = appName;
        this.start = start;
        this.hits = hits;
        this.query = query;
        this.filter = filter;
        this.sortFiled = sortFiled;
        this.sortType = sortType;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getSortFiled() {
        return sortFiled;
    }

    public void setSortFiled(String sortFiled) {
        this.sortFiled = sortFiled;
    }

    public Order getSortType() {
        return sortType;
    }

    public void setSortType(Order sortType) {
        this.sortType = sortType;
    }
}
