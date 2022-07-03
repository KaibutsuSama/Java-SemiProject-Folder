package com.kaibutsusama.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaibutsusama.reggie.common.R;
import com.kaibutsusama.reggie.dto.DishDto;
import com.kaibutsusama.reggie.entity.Category;
import com.kaibutsusama.reggie.entity.Dish;
import com.kaibutsusama.reggie.service.CategoryService;
import com.kaibutsusama.reggie.service.DishFlavorService;
import com.kaibutsusama.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private CategoryService categoryService;

    /**
     * Add dish
     * @param dishDto
     * @return
     */

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        dishService.saveWithFlavor(dishDto);
        return R.success("成功!");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){

        //构造分页构造器对象
        Page<Dish> pageInfo = new Page<>(page,pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件过滤
        queryWrapper.like(name!=null,Dish::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //执行分页查询
        dishService.page(pageInfo,queryWrapper);

        //对象Copy
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");//不将pageInfo的records拷贝到dishDtoPage是因为records是记录的所有数据,内容可见下方输出
        //接一句,目的是为了将pageInfo的records里的categoryId查询出来对应的categoryId对象并通过getName获得categoryName赋值给dishDtoPage对象
        System.out.println("Records是:"+ pageInfo.getRecords().toString());
        //Records是:
        // [Dish(id=1543395032876175362, name=da米饭, categoryId=1413384954989060097, price=123400.00, code=, image=c21ec437-459a-4643-8303-2345abdac4c1.jpg, description=, status=1, sort=0, createTime=2022-07-02T20:43:45, updateTime=2022-07-02T20:43:45, createUser=1, updateUser=1), Dish(id=1543055209376210945, name=2022, categoryId=1397844263642378242, price=202200.00, code=, image=5b95bc78-e56c-42b2-b7ed-b5f79fa446bc.jpg, description=2112, status=1, sort=0, createTime=2022-07-01T22:13:25, updateTime=2022-07-01T22:13:25, createUser=1, updateUser=1), Dish(id=1543052310717833217, name=2222, categoryId=1397844263642378242, price=222200.00, code=, image=6f8d5661-4e64-4f72-9023-130337900a88.png, description=22222, status=1, sort=0, createTime=2022-07-01T22:01:54, updateTime=2022-07-01T22:01:54, createUser=1, updateUser=1), Dish(id=1543046718305525761, name=2112, categoryId=1397844303408574465, price=11200.00, code=, image=4bf5785e-899d-482d-8019-a39c17779b8f.jpg, description=1, status=1, sort=0, createTime=2022-07-01T21:39:41, updateTime=2022-07-01T21:39:41, createUser=1, updateUser=1), Dish(id=1543046018381107201, name=1221, categoryId=1542596481917104130, price=1100.00, code=, image=ec91b374-e903-4485-b1c6-3fb1caa1d921.png, description=1, status=1, sort=0, createTime=2022-07-01T21:36:54, updateTime=2022-07-01T21:36:54, createUser=1, updateUser=1), Dish(id=1543045339868499969, name=佛挡杀佛, categoryId=1397844263642378242, price=232200.00, code=, image=aecc645d-7539-4ada-8db2-2c3c20c5fdfc.jpg, description=2, status=1, sort=0, createTime=2022-07-01T21:34:12, updateTime=2022-07-01T21:34:12, createUser=1, updateUser=1), Dish(id=1543044843460026370, name=78777, categoryId=1397844303408574465, price=777700.00, code=, image=68be6855-9fb2-4fc8-b0e1-a3e81343931a.jpg, description=7777, status=1, sort=0, createTime=2022-07-01T21:32:14, updateTime=2022-07-01T21:32:14, createUser=1, updateUser=1), Dish(id=1543043703083339778, name=6666, categoryId=1542596481917104130, price=666600.00, code=, image=ebb6583c-0a55-4907-b688-cc9c5e1c756e.jpg, description=6666, status=1, sort=0, createTime=2022-07-01T21:27:42, updateTime=2022-07-01T21:27:42, createUser=1, updateUser=1), Dish(id=1413384757047271425, name=王老吉, categoryId=1413341197421846529, price=500.00, code=, image=00874a5e-0df2-446b-8f69-a30eb7d88ee8.png, description=, status=1, sort=0, createTime=2021-07-09T14:29:20, updateTime=2021-07-12T09:09:16, createUser=1, updateUser=1), Dish(id=1413385247889891330, name=米饭, categoryId=1413384954989060097, price=200.00, code=, image=ee04a05a-1230-46b6-8ad5-1a95b140fff3.png, description=, status=1, sort=0, createTime=2021-07-09T14:31:17, updateTime=2021-07-11T16:35:26, createUser=1, updateUser=1)]

        List<Dish> records = pageInfo.getRecords();

        List<DishDto> list = records.stream().map((item)->{
            DishDto dishDto = new DishDto();
            Long categoryId = item.getCategoryId();//分类id
            BeanUtils.copyProperties(item,dishDto);
            Category category = categoryService.getById(categoryId);//根据id查询分类对象
            if(categoryId!=null) {
                String categoryName = category.getName();//获取category的Name
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }

    /**
     * 查询菜品信息和对应的口味信息ByID
     * @param id
     * @return
     */
    @GetMapping({"/{id}"})
    public R<DishDto> selectDish(@PathVariable Long id){
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> updateDish(@RequestBody DishDto dishDto){
        dishService.updateWithFlavor(dishDto);
        return R.success("成功了");
    }

    //
    @PostMapping("/status/{code}")
    public R<String> enableDish(@PathVariable("code")Integer status,@RequestParam("ids")List<Long> id){
        LambdaUpdateWrapper<Dish> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Dish::getId,id);
        updateWrapper.set(Dish::getStatus,status);

        dishService.update(updateWrapper);
        return R.success("更新状态成功");
    }

    @DeleteMapping
    public R<String> delete(@RequestParam("ids")List<Long> ids){

        dishService.deleteWithFlavor(ids);
        return R.success("删除成功");
    }
}
