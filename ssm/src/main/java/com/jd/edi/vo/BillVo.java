package com.jd.edi.vo;

import com.jd.edi.entity.Bill;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BillVo extends Bill {
    //
    private Integer page;
    private Integer limit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
