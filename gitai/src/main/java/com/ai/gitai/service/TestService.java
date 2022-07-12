package com.ai.gitai.service;

import com.ai.gitai.entity.Test;

import java.util.List;

public interface TestService {

    void insert(Test test);

    void batchInsert(List<Test> tests);

}
