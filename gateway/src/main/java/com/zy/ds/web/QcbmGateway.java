package com.zy.ds.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages= "com.zy.ds.web")
public class QcbmGateway {

	public static void main(String[] args) {
		SpringApplication.run(QcbmGateway.class, args);
	}
}
