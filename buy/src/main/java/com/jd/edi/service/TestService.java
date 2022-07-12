package com.jd.edi.service;



import com.jd.edi.entity.Test;

import java.util.List;

public interface TestService {

    void insert(Test test);

    void batchInsert(List<Test> tests);

}
