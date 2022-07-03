package com.kaibutsusama.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaibutsusama.reggie.dto.DishDto;
import com.kaibutsusama.reggie.entity.Dish;

import java.util.List;

/**
 * @author KaibutsuSama
 * @date 2022/6/30
 */
public interface DishService extends IService<Dish> {

    //新增菜品同时增加对应的口味数据，意味着要操作两张表
    public void saveWithFlavor(DishDto dishDto);

    //回显菜品同时要回显给浏览器Flavor表中的数据，意味着需要操作两张表
    public DishDto getByIdWithFlavor(Long id);

    //
    public void updateWithFlavor(DishDto dishDto);

    public void deleteWithFlavor(List<Long> ids);
}
