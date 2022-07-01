package com.kaibutsusama.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibutsusama.reggie.entity.Dish;
import com.kaibutsusama.reggie.mapper.DishMapper;
import com.kaibutsusama.reggie.service.DishService;
import org.springframework.stereotype.Service;

/**
 * @author KaibutsuSama
 * @date 2022/6/30
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
