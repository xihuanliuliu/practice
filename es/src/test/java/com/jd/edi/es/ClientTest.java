package com.jd.edi.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.edi.es.vo.Name;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ClientTest {

    private TransportClient client;

    private static final String index = "heima";
    private static final String doc = "_doc";

    private static final ObjectMapper mapper = new ObjectMapper();

    private long awaitTime = 10000;

    @Before
    public void setup() throws UnknownHostException {

        Settings settings = Settings.builder().put("cluster.name", "application").build();
        client = new PreBuiltTransportClient(settings).addTransportAddress(
                new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        System.out.println("执行");
    }

    @After
    public void after() {
        client.close();
        System.out.println("结束");
    }


    // 创建索引
    @Test
    public void createIndex() {

    }
    // 修改索引

    // 删除索引

    // 创建文档

    /**
     *   "info": "java讲师1",
     *   "email": "123456789@qq.com",
     *   "name": {
     *     "fisrtName": "zhang1",
     *     "lastName": "jing"
     *   },
     *   "age": "23"
     * @throws IOException
     */
    @Test
    public void createDoc() throws IOException {
        // 构造数据
        Map<String, Object> map = new HashMap<>();
        map.put("info", "php讲师");
        map.put("email", "123@qq.com");
        map.put("age", "213");
        map.put("name", new Name("first", "last"));
        if (map instanceof Map) {
            System.out.println("map----");
        }
        String str = mapper.writeValueAsString(map);
        System.out.println("out: " + str);

        // 构造入参的json
        String source = null;
        source = str;
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        IndexRequestBuilder builder = client.prepareIndex(index, doc, "3");
        builder = builder.setSource(source, XContentType.JSON);
        // add
        bulkRequest.add(builder);

        if (bulkRequest.numberOfActions() > 0) {
            BulkResponse responses = bulkRequest.execute().actionGet(awaitTime);
            if (responses.hasFailures()) {
                throw new RuntimeException(responses.buildFailureMessage());
            }
            System.out.println(responses.toString());
        }


    }
    // 查找数据
    @Test
    public void getDoc() {
        //数据查询
        GetResponse response = client.prepareGet(index, doc, "1").execute().actionGet();
        //得到查询的数据
        System.out.println(response.getSourceAsString());
        client.close();
    }
    // 更新文档
    @Test
    public void updateDoc() throws JsonProcessingException {
        String source = null;

        Map<String, Object> map = new HashMap<>();
        map.put("age", "123");
        String s = mapper.writeValueAsString(map);
        source = s;
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        UpdateRequestBuilder builder = client.prepareUpdate(index, doc, "1");
        builder.setDoc(source, XContentType.JSON);
        bulkRequestBuilder.add(builder);

        if (bulkRequestBuilder.numberOfActions() > 0) {
            BulkResponse responses = bulkRequestBuilder.execute().actionGet(awaitTime);
            System.out.println(responses);
        }
    }
    // 查询
    @Test
    public void getSingleDoc() {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(doc);
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet(awaitTime);

        SearchHits hits = searchResponse.getHits();
        System.out.println(hits.getHits().length);
        SearchHit[] hitsHits = hits.getHits();

        List<Map<String, Object>> res = new ArrayList<>();
        String index= hitsHits[0].getIndex();
        System.out.println("index: " + index);
        String id = hitsHits[0].getId();
        System.out.println("in: " + id);
        Stream.of(hitsHits).forEach(hit -> {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            sourceAsMap.entrySet().forEach(field -> {
                System.out.println("key: " + field.getKey() + ", val: " + field.getValue());
            });
        });
    }
    // 删除数据
    @Test
    public void deleteDoc() {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        DeleteRequestBuilder builder = client.prepareDelete(index, doc, "3");
        bulkRequest.add(builder);
        if (bulkRequest.numberOfActions() > 0) {
            System.out.println("number: " + bulkRequest.numberOfActions());
            BulkResponse responses = bulkRequest.execute().actionGet(awaitTime);
            if (responses.hasFailures()) {
                System.out.println("删除失败");
            }
            System.out.println(responses.toString());
        }
    }





}
