package com.yearbooks.supply;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className SupplyAdminApplication
 * @description TODO
 * @since 2021-10-25 23:33
 */

@SpringBootApplication
@MapperScan("com.yearbooks.supply.mapper")
public class SupplyAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupplyAdminApplication.class,args);
        //System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
