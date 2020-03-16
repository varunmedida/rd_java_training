package com.epam.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorsController implements ErrorController {
	private static final Logger LOGGER = LogManager.getLogger(ErrorsController.class);
	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletResponse response, ModelAndView modelAndView) {
		if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
			LOGGER.info("-----Page not found-----");
			modelAndView.setViewName("Error_404");
		} else if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
			modelAndView.setViewName("Error_403");
		} else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			modelAndView.setViewName("Error_500");
		} else {
			modelAndView.setViewName("Error");
		}

		return modelAndView;
	}

	@Override
	public String getErrorPath() {
		return null;
	}

}
