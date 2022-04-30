package com.jd.edi.dao;

import com.jd.edi.entity.BillType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillTypeMapper {
    //
    int saveBillType(BillType billType);

    List<BillType> findBillTypeList();

    BillType findBillTypeById(Integer id);

    int updateBillType(BillType billType);

    int deleteBillType(Integer id);

}
