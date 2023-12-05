package com.delivery.fastfood;

import com.delivery.fastfood.securities.SecurityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FastfoodDeliverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastfoodDeliverApplication.class, args);
//		System.out.println(SecurityUtils.getUserId());
	}

}
