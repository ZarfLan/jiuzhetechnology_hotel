package com.jiuzhe.app.hotel.service.aliyun.impl;

import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.OpenSearchClient;
import com.aliyun.opensearch.SearcherClient;
import com.aliyun.opensearch.sdk.dependencies.com.google.common.collect.Lists;
import com.aliyun.opensearch.sdk.dependencies.org.json.JSONArray;
import com.aliyun.opensearch.sdk.dependencies.org.json.JSONObject;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchClientException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchResult;
import com.aliyun.opensearch.sdk.generated.document.Command;
import com.aliyun.opensearch.sdk.generated.document.DocumentConstants;
import com.aliyun.opensearch.sdk.generated.search.*;
import com.aliyun.opensearch.sdk.generated.search.general.SearchResult;
import com.jiuzhe.app.hotel.module.OpenSearchQuery;
import com.jiuzhe.app.hotel.service.aliyun.OpenSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class OpenSearchImpl implements OpenSearch {
    private static final Logger logger = LoggerFactory.getLogger(OpenSearchImpl.class);

    @Autowired
    private OpenSearchClient serviceClient;

    private String document;

    @Override
    public String searchDocuments(OpenSearchQuery searchQuery) {
        //创建SearcherClient对象，并以OpenSearchClient对象作为构造参数
        SearcherClient searcherClient = new SearcherClient(serviceClient);

        //定义Config对象，用于设定config子句参数，指定应用名，分页，数据返回格式等等
        Config config = new Config(Lists.newArrayList(searchQuery.getAppName()));
        //设置返回格式为fulljson格式
        config.setSearchFormat(SearchFormat.JSON);
        //设置查询起始位置和条数
        config.setStart(searchQuery.getStart());
        config.setHits(searchQuery.getHits());

        //创建参数对象
        SearchParams searchParams = new SearchParams(config);

        //指定搜索的关键词
        searchParams.setQuery(searchQuery.getQuery());

        //设置查询过滤条件
        Optional.ofNullable(searchQuery.getFilter()).ifPresent(
                filter ->searchParams.setFilter(filter)
        );

        //设置排序
        Sort sort = new Sort();
        sort.addToSortFields(new SortField(searchQuery.getSortFiled(), searchQuery.getSortType()));
        searchParams.setSort(sort);

        //执行查询语句返回数据对象
        try {
            SearchResult searchResult = searcherClient.execute(searchParams);
            document = searchResult.getResult();
        } catch (OpenSearchException e) {
            logger.error("Search document from OpenSearch failed. query is : " + searchQuery);
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (OpenSearchClientException e) {
            logger.error(e.getMessage());
        }

        return document;
    }

    @Override
    public void operateDocuments(String appName, String tableName, Command cmd, List<Map<String, Object>> docs)
            throws IllegalArgumentException, OpenSearchClientException, OpenSearchException {
        for (Map<String, Object> doc : docs) {
            if (!doc.containsKey("id")) {
                logger.error("document lack key(id), can not add to open search!");
                throw new IllegalArgumentException("文档没有主键id");
            }

        }
        //定义DocumentClient对象添加json格式doc数据批量提交
        DocumentClient documentClient = new DocumentClient(serviceClient);

        JSONArray docsJsonArr = new JSONArray();
        for (Map<String, Object> doc : docs) {
            JSONObject jsondoc = new JSONObject();
            jsondoc.put(DocumentConstants.DOC_KEY_CMD, cmd.toString());
            jsondoc.put(DocumentConstants.DOC_KEY_FIELDS, doc);

            docsJsonArr.put(jsondoc);
        }
        String docsJson = docsJsonArr.toString();

        //执行推送操作
        OpenSearchResult osr = documentClient.push(docsJson, appName, tableName);
        //判断数据是否推送成功，主要通过判断2处，第一处判断用户方推送是否成功，第二处是应用控制台中有无报错日志
        //用户方推送成功后，也有可能在应用端执行失败，比如字段内容转换失败
        if (!osr.getResult().equalsIgnoreCase("true")) {
            logger.error("用户方推送报错！" + osr.getTraceInfo());
            throw new OpenSearchException();
        }
    }
}
