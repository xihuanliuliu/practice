package com.jd.edi.service;

import com.jd.edi.entity.Bill;
import com.jd.edi.vo.BillVo;


import java.util.List;


public interface BillService {

    List<Bill> findBillList(BillVo billVo);

    int save(Bill bill);

    int updateBill(Bill bill);

    int deleteBill(Integer id);

}
