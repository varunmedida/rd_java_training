package com.epam.util;

import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.epam.service.CategoryService;
import com.epam.service.CategoryServiceImpl;

@Configuration
@ComponentScan(basePackages = {"com.epam.service", "com.epam.dao", "com.epam.controller", "com.epam.util"})
public class Config {


	@PostConstruct
	public void getContext() {
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		CategoryService ccc = context.getBean(CategoryServiceImpl.class);
		System.out.println("testing bean================" + ccc);
	}
	
}
