package com.jd.edi.vo;

import com.jd.edi.entity.Setmeal;
import com.jd.edi.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;


}
