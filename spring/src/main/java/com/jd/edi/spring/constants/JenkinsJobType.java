package com.jd.edi.spring.constants;

public enum JenkinsJobType {
    DOWNLOAD("代码下载", 0),
    QUALITY("质量检查", 1),
    COMPILE("编译", 2),
    UNITTEST("单元测试", 3),
    PACKAGE("打包", 4),
    EXECSQL("SQL执行", 5),
    DEPLOY("应用部署", 6),
    ENVSETUP("环境搭建", 7),
    TESTING("测试", 8),
    RELEASE("应用服务发布", 9),
    SANITYTEST("冒烟测试", 10),
    PIPELINE("流水线", 11),
    OTHER("其他", 12),
    SHELL("执行shell", 13),
    NOTICE("job通知", 14),
    IMAGE("构建镜像", 15);

    private String name;
    private int index;

    // 构造方法
    private JenkinsJobType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (JenkinsJobType c : JenkinsJobType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static JenkinsJobType getJobType(int index) {
        for (JenkinsJobType c : JenkinsJobType.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
