package com.kaibutsusama.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibutsusama.reggie.entity.Employee;
import com.kaibutsusama.reggie.mapper.EmployeeMapper;
import com.kaibutsusama.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author KaibutsuSama
 * @date 2022/6/27
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {

}
