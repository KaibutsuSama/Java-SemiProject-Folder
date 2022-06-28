package com.kaibutsusama.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaibutsusama.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author KaibutsuSama
 * @date 2022/6/27
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
