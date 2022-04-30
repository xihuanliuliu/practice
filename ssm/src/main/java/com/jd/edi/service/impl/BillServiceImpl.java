package com.jd.edi.service.impl;

import com.jd.edi.dao.BillMapper;
import com.jd.edi.entity.Bill;
import com.jd.edi.service.BillService;
import com.jd.edi.vo.BillVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    private static final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

    @Resource
    private BillMapper billMapper;

    public List<Bill> findBillList(BillVo billVo) {
        return billMapper.findBillList(billVo);
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(Bill bill) {
        return billMapper.saveBill(bill);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateBill(Bill bill) {
        return billMapper.updateBill(bill);
    }
    @Transactional(rollbackFor = Exception.class)
    public int deleteBill(Integer id) {
        return billMapper.deleteBillById(id);
    }
}
