package com.cleansoft.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleansoft.demo.Filter.MyFilter;


@Configuration
public class FilterConfig {
	private static final Logger logger = LoggerFactory.getLogger(FilterConfig.class);
    @Bean
    public FilterRegistrationBean<MyFilter> filterRegistrationBean() {
        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new MyFilter());	// 这里可以使用 new，也可以在 Filter 上加 @Component 注入进来
        logger.info("加载文件路径");
        bean.addUrlPatterns("/*");
        logger.info("文件路径加载完成");
        bean.setOrder(1);	// 值越小，优先级越高
        return bean;
    }
}