package com.kaibutsusama.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaibutsusama.reggie.dto.SetmealDto;
import com.kaibutsusama.reggie.entity.Setmeal;

import java.util.List;

/**
 * @author KaibutsuSama
 * @date 2022/6/30
 */
public interface SetmealService extends IService<Setmeal> {

    public void saveWithDish(SetmealDto setmealDto);

    public void deleteWithDish(List<Long> ids);
}
