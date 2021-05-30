package com.xihua.hotpot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * 第一个注解：表示它是springboot 程序入口，main方法在这里，一切开始的地方
 * 第二个注解是mybatis框架自动扫码 mapper接口的路径，它是一个包路径
 */
@SpringBootApplication
@MapperScan("com.xihua.hotpot.mapper")
public class HotpotApplication {

    // 程序启动的地方，固定写法
    public static void main(String[] args) {
        SpringApplication.run(HotpotApplication.class, args);
    }

}
