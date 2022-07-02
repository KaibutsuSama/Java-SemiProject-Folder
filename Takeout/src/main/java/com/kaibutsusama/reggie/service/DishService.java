package com.kaibutsusama.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaibutsusama.reggie.dto.DishDto;
import com.kaibutsusama.reggie.entity.Dish;

/**
 * @author KaibutsuSama
 * @date 2022/6/30
 */
public interface DishService extends IService<Dish> {

    //新增菜品同时增加对应的口味数据，意味着要操作两张表
    public void saveWithFlavor(DishDto dishDto);
}
