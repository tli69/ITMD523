package com.item.backend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableSwagger2
public class BookBackendApiV1Application {
    public static void main(String[] args) {
        SpringApplication.run(BookBackendApiV1Application.class, args);
    }
    }

