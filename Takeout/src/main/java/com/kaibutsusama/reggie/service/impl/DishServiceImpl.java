package com.kaibutsusama.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibutsusama.reggie.dto.DishDto;
import com.kaibutsusama.reggie.entity.Dish;
import com.kaibutsusama.reggie.entity.DishFlavor;
import com.kaibutsusama.reggie.mapper.DishMapper;
import com.kaibutsusama.reggie.service.DishFlavorService;
import com.kaibutsusama.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author KaibutsuSama
 * @date 2022/6/30
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    @Transactional
    @Override
    public void saveWithFlavor (DishDto dishDto) {
        //保存菜品基本信息到dish表
        this.save(dishDto);

        Long dishId = dishDto.getId();//菜品id for dish_flavor

        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();//将本该赋值给Dish表的flavor参数赋值给DishFlavor表

//        flavors.stream().map((item) -> {
//            item.setDishId(dishId);
//            return item;
//        }).collect(Collectors.toList());
//        System.out.println("stream"+flavors.toString());

        //用lambda表达式好像比foreact表达式的时间复杂度低

        for (DishFlavor flavor : flavors) {
            flavor.setDishId(dishId);
            System.out.println("foreact"+flavor.toString());
        }


        //保存菜品数据到dish_flavor表
        dishFlavorService.saveBatch(flavors);
    }
}
