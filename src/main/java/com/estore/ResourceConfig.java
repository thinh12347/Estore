package com.estore;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
	@Bean(name = "messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasenames("classpath:static/i18n/account", "classpath:static/i18n/layout","classpath:static/i18n/message");
		ms.setDefaultEncoding("utf-8");
		return ms;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor inter = new LocaleChangeInterceptor();
		inter.setParamName("lang");
		registry.addInterceptor(inter).addPathPatterns("/home/language");
	}

	@Bean("localeResolver")
	public LocaleResolver getLocaleResolver() {
		CookieLocaleResolver r = new CookieLocaleResolver();
		r.setCookiePath("/");
		r.setCookieMaxAge(2 * 60 * 60);
		r.setDefaultLocale(new Locale("vi"));
		return r;
	}
}
