package com.ai.gitai.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@ToString(callSuper = true)
public class ChildUser extends User {
    private String child;

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }
}
