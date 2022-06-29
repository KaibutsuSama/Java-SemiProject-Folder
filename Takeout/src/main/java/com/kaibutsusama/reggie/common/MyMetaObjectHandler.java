package com.kaibutsusama.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.kaibutsusama.reggie.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元数据对象处理器
 * @author KaibutsuSama
 * @date 2022/6/29
 */

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill (MetaObject metaObject) {
        log.info("InsertFill");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", new Long(1));
        metaObject.setValue("updateUser", new Long(1));
    }

    @Override
    public void updateFill (MetaObject metaObject) {
        log.info("UpdateFill");
        log.info(metaObject.toString());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", new Long(1));

    }
}
