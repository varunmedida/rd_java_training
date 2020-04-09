package com.epam.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.adminservice.AdminProductService;

@RestController
@RequestMapping(path = "/api")
public class AdminProductController {

	private final Logger log = LogManager.getLogger(AdminProductController.class);
	
	@Autowired
	AdminProductService adminProductService;
	
}
