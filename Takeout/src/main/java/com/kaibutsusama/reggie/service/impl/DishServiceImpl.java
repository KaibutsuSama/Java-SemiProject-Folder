package com.kaibutsusama.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibutsusama.reggie.common.CustomException;
import com.kaibutsusama.reggie.dto.DishDto;
import com.kaibutsusama.reggie.entity.Dish;
import com.kaibutsusama.reggie.entity.DishFlavor;
import com.kaibutsusama.reggie.mapper.DishMapper;
import com.kaibutsusama.reggie.service.DishFlavorService;
import com.kaibutsusama.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
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

    @Override
    public DishDto getByIdWithFlavor (Long id) {
        //从dish表查询菜品基本信息
        Dish dish = this.getById(id);//dish为基本信息

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);

        //查询当前菜品对应的Flavor信息从dish_flavor表中查询
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);

        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Override
    public void updateWithFlavor (DishDto dishDto) {

        //更新dish表基本信息
        this.updateById(dishDto);
        //清理当前dish_flavor数据
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);
        //重新添加提交当前的数据
        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors = flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }

    @Transactional
    @Override
    public void deleteWithFlavor (List<Long> ids) {

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //1.查询所有的在售商品
        queryWrapper.eq(Dish::getStatus,1);
        //2.查询id是否在未停售
        queryWrapper.in(Dish::getId,ids);

        if(this.count(queryWrapper) > 0){
            throw new CustomException("该列表还有未停售的商品");
        }
        //3.移除菜品
        this.removeByIds(ids);
        //4.移除菜品口味
        LambdaQueryWrapper<DishFlavor> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(DishFlavor::getDishId,ids);
        dishFlavorService.remove(queryWrapper1);
    }


}
