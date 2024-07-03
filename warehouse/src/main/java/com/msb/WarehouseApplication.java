package com.msb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dh
 * @date 2024/7/3
 */
@SpringBootApplication
@MapperScan("com.msb.mapper")
public class WarehouseApplication {
    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }
}
