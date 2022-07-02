package com.kaibutsusama.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibutsusama.reggie.entity.DishFlavor;
import com.kaibutsusama.reggie.mapper.DishFlavorMapper;
import com.kaibutsusama.reggie.mapper.DishMapper;
import com.kaibutsusama.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @author KaibutsuSama
 * @date 2022/7/1
 */
@Service
public class DishFlavorIServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
