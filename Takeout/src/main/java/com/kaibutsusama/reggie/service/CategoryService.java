package com.kaibutsusama.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaibutsusama.reggie.entity.Category;

/**
 * @author AsheOne
 * @date 2022/6/30
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
