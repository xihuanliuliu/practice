package com.jd.edi;

import com.jd.edi.annotation.AutoIdempotent;
import lombok.ToString;

@ToString
public class TPerson {
    private String name;
    private String age;

    @AutoIdempotent
    public String getTPerson() {
        return "name";
    }
    public String getName() {
        return "getName";
    }

    public String getAge(String age) {
        this.age = age;
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
