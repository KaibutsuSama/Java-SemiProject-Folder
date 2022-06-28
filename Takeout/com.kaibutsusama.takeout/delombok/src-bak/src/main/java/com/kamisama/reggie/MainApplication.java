package com.kaibutsusama.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author AsheOne
 * @date 2022/6/27
 */

@Slf4j
@SpringBootApplication
public class MainApplication {
    public static void main (String[] args) {
        SpringApplication.run(MainApplication.class,args);
        log.info("启动成功");
    }
}
