package com.ai.gitai.entity;

import lombok.Data;

@Data
public class Cat {

    private String address;

    public String getAddress() {
        return address;
    }

    public Cat setAddress(String address) {
        this.address = address;
        return this;
    }
}
