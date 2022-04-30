package com.jd.edi.dao;

import com.jd.edi.entity.Bill;
import com.jd.edi.vo.BillVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillMapper {

    // 添加
    int saveBill(Bill bill);

    // 查询,根据分页查询
    List<Bill> findBillList(BillVo billVo);

    // 修改
    int updateBill(Bill bill);

    // 删除
    int deleteBillById(Integer id);

    //

}
