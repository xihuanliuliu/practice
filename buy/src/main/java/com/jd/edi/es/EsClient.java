package com.jd.edi.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.edi.entity.HotelDoc;
import com.jd.edi.exception.EsException;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


public class EsClient implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(EsClient.class);
    // 地址
    private String address;

    // 端口
    private int port;

    // type
    private static final String ES_TYPE = "doc";

    // 集群
    private String clusterName;
    // 用户名-密码
    private String userName;
    private String password;

    // 等待时间
    private long awaitTime = 1000;

    private TransportClient client;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 设置ES请求的验证参数
     */
    private static final String Key = "request.headers.Authorization"; //固定值

    public long getAwaitTime() {
        return awaitTime;
    }

    public void setAwaitTime(long awaitTime) {
        this.awaitTime = awaitTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static String getEsType() {
        return ES_TYPE;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    private void initEsClient() throws UnknownHostException {
        // setting es client
        Settings settings = Settings.builder().put("cluster.name", "application").build();
        client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress
                (InetAddress.getByName(this.address), this.port));
        logger.info("初始化es client");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initEsClient();
    }


    public void insertSingleData(String index, HotelDoc object) throws JsonProcessingException {
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

        String source = null;
        source  = objectMapper.writeValueAsString(object);
        System.out.println("source: " + source);

        IndexRequestBuilder builder = client.prepareIndex(index, ES_TYPE, String.valueOf(object.getId()));

        builder.setSource(source, XContentType.JSON);
        bulkRequestBuilder.add(builder);
        if (bulkRequestBuilder.numberOfActions() > 0) {
            BulkResponse responses = bulkRequestBuilder.execute().actionGet(awaitTime);
            if (responses.hasFailures()) {
                System.out.println(responses.status());
                System.out.println(responses.buildFailureMessage());
            }
        }
    }


    public String getSingleDoc(String index, Long id) {
        GetResponse response = client.prepareGet().setIndex(index).setType(ES_TYPE).setId(String.valueOf(id)).get();
        return response.toString();
    }


    public void queryDataByCondition(String index, QueryCondition condition) {
        SearchRequestBuilder builder = client.prepareSearch(index).setTypes(ES_TYPE);


        // 分页
        if (StringUtils.isNotEmpty(condition.getPageNum())) {
            builder.setFrom(Integer.parseInt(condition.getPageNum()));
        }
        if (StringUtils.isNotEmpty(condition.getPageSize())) {
            builder.setSize(Integer.parseInt(condition.getPageSize()));
        }

        // 排序
        if (StringUtils.isNotEmpty(condition.getFiled())) {
            builder.addSort(condition.getFiled(), condition.getSort() == null ||
                    condition.getSort().equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC);

        }
        // bool
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (StringUtils.isNotEmpty(condition.getBoolValue())) {
            boolQuery.must(QueryBuilders.matchQuery(condition.getBoolKey() ,condition.getBoolValue()));
        }

        builder.setQuery(boolQuery);

        // 过滤
        if (StringUtils.isNotEmpty(condition.getFilterName())) {
            addFilter(builder, condition);
        }

        SearchResponse response = builder.execute().actionGet();
        SearchHits responseHits = response.getHits();
        System.out.println("size: " + responseHits.totalHits);
        SearchHit[] hits = responseHits.getHits();
        System.out.println("current size: " + hits.length);
        Stream.of(hits).forEach(hit -> {
            System.out.println("------------------------------");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            sourceAsMap.entrySet().forEach( sources -> {
                System.out.println("key: " + sources.getKey() + ", val: " + sources.getValue());
            });

        });
    }

    private void addFilter(SearchRequestBuilder builder, QueryCondition condition) {
        TermsAggregationBuilder group = AggregationBuilders.terms(condition.getFilterKey()).field(condition.getFilterName());
    
        builder.addAggregation(group);
    }

    public static String generateBaseCode(String userName, String password) {
        StringBuilder builder = new StringBuilder();
        builder.append(userName).append(":").append(password);
        byte[] bytes = null;
        try {
            bytes = builder.toString().getBytes(StandardCharsets.UTF_8);
            byte[] encode = Base64.getEncoder().encode(bytes);
            String token = new String(encode);
            return "Basic " + token;
        } catch (Exception e) {
            logger.error("");
        }
        return "Basic ";
    }

    public static void main(String[] args) {
        String s = generateBaseCode("user", "pass");
        System.out.println(s);
    }
}
