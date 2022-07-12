package com.ai.aif.gitai;

import com.ai.aif.gitai.dao.mapper.TestMapper;
import com.ai.aif.gitai.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class TestServiceIm {

    @Autowired
    private TestService testService;
    @Autowired
    private TestMapper testMapper;
    @Test
    public void insertTest() {
        com.ai.aif.gitai.dao.entity.Test test = new com.ai.aif.gitai.dao.entity.Test();
        int total = 3000;
        int maxInsertItemNumPerTime = 100;
        long startTime = System.currentTimeMillis();    //获取开始时间
        List<com.ai.aif.gitai.dao.entity.Test> list = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            test.setBuName("bu" + i);
            test.setActive("act" + i);
            test.setCode("code" + i);
            test.setProjId((long)i);
            test.setProjLevel(String.valueOf(i));
            test.setTotalCommit((long)i);
            test.setProjStatus(String.valueOf(i));
            test.setRepoName("repoe" + i);
            test.setRepoUrl("utl" + i);
            test.setProjTime(" tme" + i);
            test.setRepoTime("tie" + i);
            test.setProjName("projme" + i);
            test.setStatisticMonth("moth" + i);
            test.setBranchSize( i);
            test.setCreateTime(new Date());
            list.add(test);
        }

        // 分批次的批量插入
        try {
            if (list.size() > maxInsertItemNumPerTime) {
                List<List<com.ai.aif.gitai.dao.entity.Test>> all = new ArrayList<>();
                int i = 0;
                while (i < list.size()) {
                    List subList = list.subList(i, i + maxInsertItemNumPerTime);
                    i = i + maxInsertItemNumPerTime;
                    System.out.println("i: " + i);
                    all.add(subList);
                }
                all.parallelStream().forEach(o -> testMapper.batchInsert(o));
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");

    }

    @Test
    public void insertOneTest() {
        long startTime = System.currentTimeMillis();    //获取开始时间
        int total = 3000;
        List<com.ai.aif.gitai.dao.entity.Test> list = new ArrayList<>(total);

        for (int i = 0; i < total; i++) {
            com.ai.aif.gitai.dao.entity.Test test = new com.ai.aif.gitai.dao.entity.Test();
            test.setBuName("bue" + i);
            test.setActive("act" + i);
            test.setCode("code" + i);
            test.setProjId((long)i);
            test.setProjLevel(String.valueOf(i));
            test.setTotalCommit((long)i);
            test.setProjStatus(String.valueOf(i));
            test.setRepoName("repoName" + i);
            test.setRepoUrl("utl" + i);
            test.setProjTime(" time" + i);
            test.setRepoTime("time" + i);
            test.setProjName("projName" + i);
            test.setStatisticMonth("moth" + i);
            test.setBranchSize(i);
            test.setCreateTime(new Date());
            list.add(test);
        }
        testMapper.batchInsert(list);
    }





    @Test
    public void testEmpty() {
        List<String> list = new ArrayList<>();
        list.add("");
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("----------");
        } else {
            System.out.println("==============");
        }


    }
}
