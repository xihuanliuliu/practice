package com.jd.edi.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jd.edi.auth.AuthContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充-insert");
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("createUser", AuthContext.currentUser());
        metaObject.setValue("updateUser",AuthContext.currentUser());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充-update");
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser",AuthContext.currentUser());
    }
}
