package com.estore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.estore.interceptor.AuthorInterceptor;
import com.estore.interceptor.ShareInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	@Autowired
	ShareInterceptor shareInterceptor;
	@Autowired
	AuthorInterceptor auth;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(shareInterceptor).addPathPatterns("/**");
		registry.addInterceptor(auth)
		.addPathPatterns("/account/change","/account/logoff","/account/edit","/order/**");
	}
}
