package com.jd.edi;

import com.jd.edi.entity.Bill;
import com.jd.edi.service.BillService;
import com.jd.edi.service.impl.BillServiceImpl;
import com.jd.edi.vo.BillVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 第一种方式，使用
 *
 * new ClassPathXmlApplicationContext("applicationContext.xml");
 * 它会装载并实例化上下文的bean。
 *
 * 第二种方式：在测试类中加入一个基类
 *
 * 写上注解
 *
 * @RunWith(SpringJUnit4ClassRunner.class)
 * // 这个classpath，如果测试的是service就放spring的配置文件，如果是controller就放springmvc的配置文件：
 * @ContextConfiguration(locations = { “classpath:conf/applicationContext.xml”, “classpath:conf/mybatis.xml” })
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml", "classpath:mybatis-config.xml"})
public class ServiceTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private BillService billService;

    @Test
    public void test1() {

        BillVo vo = new BillVo();
        vo.setPage(1);
        vo.setLimit(10);
        List<Bill> billList = billService.findBillList(vo);
        System.out.println("----");
        Assert.assertNotNull("确实为空", billList);
//        for (Bill bill : billList) {
//            System.out.println(bill.toString());
//        }
        System.out.println("===============");
    }
}
