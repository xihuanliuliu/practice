package com.jd.edi.utils;

public interface CacheNames extends OauthCacheNames {
    /**
     *
     * 参考CacheKeyPrefix
     * cacheNames 与 key 之间的默认连接字符
     */
    String UNION = "::";

    /**
     * key内部的连接字符（自定义）
     */
    String UNION_KEY = ":";
}
