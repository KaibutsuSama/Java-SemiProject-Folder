package com.kaibutsusama.reggie.dto;

import com.kaibutsusama.reggie.entity.Setmeal;
import com.kaibutsusama.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
