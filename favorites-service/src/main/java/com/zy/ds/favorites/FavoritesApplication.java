package com.zy.ds.favorites;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.zy.ds.favorites.persist.dao")
public class FavoritesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavoritesApplication.class, args);
	}

}
