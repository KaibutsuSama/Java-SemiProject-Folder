package com.kaibutsusama.reggie.common;

/**
 * 自定义业务异常
 * @author KaibutsuSama
 * @date 2022/6/30
 */
public class CustomException extends RuntimeException{

    public CustomException(String message){
        super(message);
    }
}
