package com.estore;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class GmailConfig {
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl impl = new JavaMailSenderImpl();
		impl.setDefaultEncoding("utf-8");
		impl.setHost("smtp.gmail.com");
		impl.setPort(587);
		impl.setUsername("nguyenduythinh0101999@gmail.com");
		impl.setPassword("nguyenduythinh");
		Properties properties = impl.getJavaMailProperties();
		properties.setProperty("mail.transport.protocol","smtp");
		properties.setProperty("mail.smtp.auth","true");
		properties.setProperty("mail.smtp.starttls.enable","true");
		properties.setProperty("mail.debug","true");
		return impl;
	}
}
