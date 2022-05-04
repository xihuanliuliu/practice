package com.jd.edi.auth;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class test {
    static Set<String> set = new HashSet<>();

    public static boolean isExcludePath (String path) {
        set.add("/front/");
        set.add("/backend/page/login/login.html");
        set.add("/backend/");
        for (String excludePath : set) {
            System.out.println("ex: " + excludePath);
            System.out.println("path: " + path);
            if (path.startsWith(excludePath)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String originName = "me.png";
        String uuid = UUID.randomUUID().toString().substring(0, 13);

        String fileName = uuid + originName.substring(originName.lastIndexOf("."));
        System.out.println(fileName);
    }


}
