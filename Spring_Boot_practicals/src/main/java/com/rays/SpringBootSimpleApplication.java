package com.rays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rays.common.FrontCtl;

@SpringBootApplication
public class SpringBootSimpleApplication {
	
	
	@Autowired
	FrontCtl frontCtl;
	

	public static void main(String[] args) {

		SpringApplication.run(SpringBootSimpleApplication.class, args);
		System.out.println("spring boot application started at: http://localhost:8080");

	}

	@Bean
	public WebMvcConfigurer webConfig() {

		WebMvcConfigurer w = new WebMvcConfigurer() {

			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(frontCtl).addPathPatterns("/**").excludePathPatterns("/Auth/**");
			}

		};

		return w;

	}

}