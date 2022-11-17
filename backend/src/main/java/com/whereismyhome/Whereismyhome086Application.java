package com.whereismyhome;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(basePackages = "com.whereismyhome.model.dao", annotationClass = Mapper.class)
@SpringBootApplication
public class Whereismyhome086Application {

	public static void main(String[] args) {
		SpringApplication.run(Whereismyhome086Application.class, args);
	}

}
