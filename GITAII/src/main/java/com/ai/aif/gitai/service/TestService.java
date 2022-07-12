package com.ai.aif.gitai.service;

import com.ai.aif.gitai.dao.entity.Test;

import java.util.List;

public interface TestService {

    void insert(Test test);

    void batchInsert(List<Test> tests);

}
