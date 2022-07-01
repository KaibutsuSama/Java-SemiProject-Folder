package com.kaibutsusama.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibutsusama.reggie.entity.Setmeal;
import com.kaibutsusama.reggie.mapper.SetmealMapper;
import com.kaibutsusama.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

/**
 * @author KaibutsuSama
 * @date 2022/6/30
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
