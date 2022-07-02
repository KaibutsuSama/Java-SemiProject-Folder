package com.kaibutsusama.reggie.dto;

import com.kaibutsusama.reggie.entity.Dish;
import com.kaibutsusama.reggie.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


/**
 * @author KaibutsuSama
 * @date 2022/7/01
 * DishFlavor
 */
@Data
public class DishDto extends Dish {

    //菜品对应的口味数据
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
