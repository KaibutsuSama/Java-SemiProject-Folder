package com.kaibutsusama.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author KaibutsuSama
 * @date 2022/6/27
 */

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class MainApplication {
    public static void main (String[] args) {
        SpringApplication.run(MainApplication.class,args);
        log.info("启动成功");
    }
}
