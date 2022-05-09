package com.jd.edi.socket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {

    /**
     * 登录的标志位  成功/失败
     */
    private boolean flag;

    /**
     * 登录的响应消息
     */
    private String message;
}
