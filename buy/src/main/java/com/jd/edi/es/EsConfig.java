package com.jd.edi.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "es")
public class EsConfig {
    private static final Logger logger = LoggerFactory.getLogger(EsConfig.class);

    // ConfigurationProperties要起作用，需要实现get-set方法
    private EsProperties detail;
    // 在外面注入时使用detailLogEs
    @Bean
    public EsClient detailLogEs() {
        logger.info("es properties: {}", detail.toString());
        EsClient esClient = new EsClient();
        esClient.setPort(detail.getPort());
        esClient.setAddress(detail.getIp());
        return esClient;
    }


    public EsProperties getDetail() {
        return detail;
    }

    public void setDetail(EsProperties detail) {
        this.detail = detail;
    }
}
