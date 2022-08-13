package com.jd.edi.service.impl;

import com.jd.edi.exception.RedisException;
import com.jd.edi.service.TokenService;
import com.jd.edi.utils.CacheNames;
import com.jd.edi.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service("tokenService")
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Override
    public String createToken() {
        String str = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        System.out.println("创建");
//        try{
//            token.append(CacheNames.ACCESS).append(str);
//            String key  = CacheNames.ACCESS;
//            RedisUtil.set(key, token.toString(),10000L);
//            return token.toString();
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
        return null;
    }
    /**
     * 检验token
     *
     * @param request
     * @return
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws RedisException {
        String token = request.getHeader(CacheNames.ACCESS);
        System.out.println("token: " + token);
//        if(StringUtils.isBlank(token)) {// header中不存在token
//            token = request.getParameter(CacheNames.ACCESS);
//            if(StringUtils.isBlank(token)) {// parameter中也不存在token
//                throw new RedisException("", "100");
//            }
//        }
        RedisUtil.set(CacheNames.ACCESS, UUID.randomUUID().toString(), 100L);
        System.out.println("执行完开始 " + RedisUtil.get(CacheNames.ACCESS));
        if(!RedisUtil.hasKey(CacheNames.ACCESS)) {
            System.out.println("key 不存在");
            throw new RedisException("", "200");
        }
        RedisUtil.del(CacheNames.ACCESS);
        System.out.println("执行完成 " + RedisUtil.get(CacheNames.ACCESS));

        return true;
    }
}
