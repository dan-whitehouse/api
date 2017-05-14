package org.ricone.api.config;

import org.ricone.api.component.logging.LogHandler;
import org.ricone.api.controller.HeaderHandler;
import org.ricone.api.security.AuthHandler;
import org.ricone.api.security.test.SecurityConfig;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.EndpointWebMvcAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.HealthIndicatorAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.ManagementServerPropertiesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = "org.ricone.api")
@Import({
		EndpointWebMvcAutoConfiguration.class,
		ManagementServerPropertiesAutoConfiguration.class, 
		EndpointAutoConfiguration.class, 
		HealthIndicatorAutoConfiguration.class
		})
@PropertySource("classpath:application.properties")
@EnableWebMvc
@EnableScheduling
public class Config extends WebMvcConfigurerAdapter 
{
	
	@Bean(name="viewProject")
	public ViewResolver viewResolver() 
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) 
    {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

	@Override
    public void addInterceptors(InterceptorRegistry registry) 
    {   	
    	HandlerInterceptor authHandler = new AuthHandler();
    	HandlerInterceptor headerHandler = new HeaderHandler();
    	HandlerInterceptor logHandler = new LogHandler();
    	
		registry.addInterceptor(authHandler);
		registry.addInterceptor(headerHandler);
		registry.addInterceptor(logHandler);
	}
}
