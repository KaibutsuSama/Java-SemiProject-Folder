package com.kaibutsusama.reggie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaibutsusama.reggie.common.R;
import com.kaibutsusama.reggie.dto.DishDto;
import com.kaibutsusama.reggie.service.DishFlavorService;
import com.kaibutsusama.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author KaibutsuSama
 * @date 2022/7/1
 */

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;
    /*代办事项
    1. 图片的上传和下载
    3. 删除菜品
    4. 批量新增
    5. 批量删除
    6. 批量停售
     */

    /**
     * Add dish
     * @param dishDto
     * @return
     */

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return R.success("成功!");
    }




}
