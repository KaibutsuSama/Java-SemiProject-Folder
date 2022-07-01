package com.kaibutsusama.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibutsusama.reggie.common.CustomException;
import com.kaibutsusama.reggie.entity.Category;
import com.kaibutsusama.reggie.entity.Dish;
import com.kaibutsusama.reggie.entity.Setmeal;
import com.kaibutsusama.reggie.mapper.CategoryMapper;
import com.kaibutsusama.reggie.service.CategoryService;
import com.kaibutsusama.reggie.service.DishService;
import com.kaibutsusama.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KaibutsuSama
 * @date 2022/6/30
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    /**
     * Delete by ID,但是Delete之前需要进行判断
     * @param id
     */

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove (Long ids) {
        //创建条件对象
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件,根据分类id开始查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,ids);
        //查询当前分类是否关联了其它菜品，如果已经关联，则抛出业务异常
        int countForDish = dishService.count(dishLambdaQueryWrapper);
        if(countForDish>0){
            // 抛出异常
            throw new CustomException("当前分类下关联了菜品，无法删除");
        }


        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询当前分类是否关联了其它套餐，如果已经关联，则抛出业务异常
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,ids);
        int countForSetmeal = setmealService.count(setmealLambdaQueryWrapper);
        if(countForSetmeal>0){
           // 抛出异常
            throw new CustomException("当前分类下关联了套餐，无法删除");

        }

        //正常删除
        super.removeById(ids);
    }
}
