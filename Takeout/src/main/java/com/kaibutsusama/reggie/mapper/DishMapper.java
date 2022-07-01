package com.kaibutsusama.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaibutsusama.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author KaibutsuSama
 * @date 2022/6/30
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
