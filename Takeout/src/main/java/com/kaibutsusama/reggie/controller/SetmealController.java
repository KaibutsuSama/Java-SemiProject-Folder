package com.kaibutsusama.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaibutsusama.reggie.common.R;
import com.kaibutsusama.reggie.dto.SetmealDto;
import com.kaibutsusama.reggie.entity.Category;
import com.kaibutsusama.reggie.entity.Setmeal;
import com.kaibutsusama.reggie.service.CategoryService;
import com.kaibutsusama.reggie.service.SetmealDishService;
import com.kaibutsusama.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author KaibutsuSama
 * @date 2022/7/3
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    SetmealService setmealService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/page")
    public R<Page> page(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, String name){
        //1.构建分页对象
        Page<Setmeal> pageInfo = new Page(page, pageSize);
        //2.条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name),Setmeal::getName,name).orderByDesc(Setmeal::getCreateTime);
        //3.执行分页查询
        setmealService.page(pageInfo,queryWrapper);

        Page<SetmealDto> setmealDtoPage = new Page<>(page, pageSize);
        //4.复制属性
        BeanUtils.copyProperties(pageInfo,setmealDtoPage,"records");
        setmealDtoPage.setRecords(pageInfo.getRecords().stream().map(item->{
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item,setmealDto);
            Category category = categoryService.getById(item.getCategoryId());
            if(category!=null){
            setmealDto.setCategoryName(category.getName());
            }
            return setmealDto;
        }).collect(Collectors.toList()));
        return R.success(setmealDtoPage);
    }

    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDto);
        return R.success("YES!!!");
    }

    @DeleteMapping
    public R<String> delete(@RequestParam("ids") List<Long> id){
        setmealService.deleteWithDish(id);
        return R.success("成功");
    }

    // 批量修改套餐状态
    @PostMapping("status/{code}")
    public R<String> BatchModifyMealSet(@PathVariable("code") Integer code, @RequestParam("ids") List<Long> ids) {
        // 1. 设置更新条件
        LambdaUpdateWrapper<Setmeal> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Setmeal::getStatus, code);
        wrapper.in(Setmeal::getId, ids);
        // 2. 更新
        setmealService.update(wrapper);
        return R.success("更新套餐状态成功");
    }
}
