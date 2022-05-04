package com.jd.edi.auth;

import java.util.concurrent.TimeUnit;

public interface AuthConstant {
    String AUTH_TYPE = "erp";
    String COOKIE_NAME  = AuthConstant.AUTH_TYPE + ".cookie";
    long COOKIE_EXPIRED = TimeUnit.HOURS.toSeconds(12);

}
