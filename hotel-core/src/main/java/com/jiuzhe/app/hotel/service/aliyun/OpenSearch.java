package com.jiuzhe.app.hotel.service.aliyun;

import com.aliyun.opensearch.sdk.generated.commons.OpenSearchClientException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchException;
import com.aliyun.opensearch.sdk.generated.document.Command;
import com.jiuzhe.app.hotel.module.OpenSearchQuery;

import java.util.List;
import java.util.Map;

public interface OpenSearch {
    String searchDocuments(OpenSearchQuery searchQuery);

    void operateDocuments(String appName, String tableName, Command cmd, List<Map<String, Object>> docs)
            throws IllegalArgumentException, OpenSearchClientException, OpenSearchException;
}
