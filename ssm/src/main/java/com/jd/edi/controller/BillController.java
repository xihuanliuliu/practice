package com.jd.edi.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jd.edi.entity.Bill;
import com.jd.edi.service.BillService;
import com.jd.edi.utils.DataGridViewResult;
import com.jd.edi.vo.BillVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {
    private static final Logger logger = LoggerFactory.getLogger(BillController.class);


    @Resource
    private BillService billService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public DataGridViewResult getBillList(BillVo billVo) {
        PageHelper.startPage(billVo.getPage(), billVo.getLimit());
        List<Bill> billList = billService.findBillList(billVo);
        PageInfo<Bill> billPageInfo = new PageInfo<Bill>(billList);
        logger.info("");
        return new DataGridViewResult(billPageInfo.getTotal(), billPageInfo.getList());
    }


}
