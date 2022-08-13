package com.jd.edi.es.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.edi.es.vo.EsMessage;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

public class EsClient implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(EsClient.class);
    private static final EsClient commonEsQuery = new EsClient();
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter fullFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private TransportClient transportClient;
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * es的基本文档类型
     */
    private static final String ES_TYPE = "doc";


    /**
     * 设置ES请求的验证参数
     */
    private static final String Key = "request.headers.Authorization"; //固定值


    private String address;

    private long awaitTime;


    private String clusterName;

    private int port;

    private String userName;

    private String password;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getAwaitTime() {
        return awaitTime;
    }

    public void setAwaitTime(long awaitTime) {
        this.awaitTime = awaitTime;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isEmpty(address)) {
            throw new RuntimeException("ES host name is empty!");
        }
        Settings.Builder builder = Settings.builder().put("cluster.name", clusterName);

        if (StringUtils.isNotEmpty(this.userName) && StringUtils.isNotEmpty(this.password)) {
            builder.put(Key, basicAuthHeaderValue(this.userName, this.password));
        }
        Settings settings = builder.build();
        transportClient = new PreBuiltTransportClient(settings);
        logger.warn("config:=" + settings);
        initTransportAddress(address.split("[,;]"), port);
    }

    private void initTransportAddress(String[] hostNames, int port) {
        for (int i = 0; i < hostNames.length; i++) {
            try {
                transportClient.addTransportAddress(new TransportAddress(
                        InetAddress.getByName(hostNames[i]), port));
            } catch (UnknownHostException ex) {
                logger.warn("Unkown host name: " + hostNames[i]);
            }
        }
    }


    public void insertSingleData(String esIndex, Object businessLog, String uniqueKey) {
        EsMessage esMessage = null;
        try {
            BulkRequestBuilder bulkRequest = transportClient.prepareBulk();
            IndexRequestBuilder builder = transportClient.prepareIndex(esIndex, ES_TYPE);
            if (StringUtils.isNotEmpty(uniqueKey)) {
                builder = builder.setId(uniqueKey);
            }
            String source = null;
            try {
                if (businessLog instanceof Map) {
                    ((Map) businessLog).put("smartlog_ip", "localhost");
                }
                source = businessLog instanceof String ? (String) businessLog : mapper.writeValueAsString(businessLog);
            } catch (JsonProcessingException ex) {
                logger.warn("Json 序列化失败，尝试直接存储为String. 错误消息:{}", ex.getMessage());
                source = businessLog.toString();
            }
            esMessage = new EsMessage(esIndex, uniqueKey, source);
            builder = builder.setSource(source, XContentType.JSON);
            bulkRequest.add(builder);
            if (bulkRequest.numberOfActions() > 0) {
                BulkResponse responses = bulkRequest.execute().actionGet(awaitTime);
                if (responses.hasFailures()) {
                    throw new RuntimeException(responses.buildFailureMessage());
                }
            }
        } catch (RuntimeException e) {
            // ESWriteFailedHandleChain.handle(esMessage);
            logger.error("插入ES失败， Index:[{}], 错误消息:{}", esIndex, e.getMessage(), e);
            throw e;
        }
    }
            /**
             * 基础的base64生成
             *
             * @param username 用户名
             * @param passwd   密码
             * @return
             */
    private static String basicAuthHeaderValue(String username, String passwd) {
        CharBuffer chars = CharBuffer.allocate(username.length() + passwd.length() + 1);
        byte[] charBytes = null;
        try {
            chars.put(username).put(':').put(passwd.toCharArray());
            charBytes = toUtf8Bytes(chars.array());
            String basicToken = new String(Base64.getEncoder().encode(charBytes));
            return "Basic " + basicToken;
        } finally {
            Arrays.fill(chars.array(), (char) 0);
            if (charBytes != null) {
                Arrays.fill(charBytes, (byte) 0);
            }
        }
    }


    public static

    public static byte[] toUtf8Bytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(byteBuffer.array(), (byte) 0);
        return bytes;
    }
}
