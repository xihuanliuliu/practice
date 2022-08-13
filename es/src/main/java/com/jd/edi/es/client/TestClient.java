package com.jd.edi.es.client;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestClient {




    public static void main(String[] args) throws UnknownHostException {
        //指定ES集群
        Settings setting = Settings.builder().put("cluster.name", "application").build();
        //创建访问es服务器的客户端
        TransportClient client = new PreBuiltTransportClient(setting)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        //数据查询
        GetResponse response = client.prepareGet("heima", "_doc", "1").execute().actionGet();
        //得到查询的数据
        System.out.println("sxx：" +response.getSourceAsString());
        client.close();
    }
}
