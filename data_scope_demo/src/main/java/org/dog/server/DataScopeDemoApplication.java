package org.dog.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "org.dog.server.mapper.*")
@SpringBootApplication
public class DataScopeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataScopeDemoApplication.class, args);
    }

}
