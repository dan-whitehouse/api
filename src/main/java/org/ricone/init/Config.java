package org.ricone.init;

import org.ricone.authentication.AuthHandler;
import org.ricone.authentication.HeaderHandler;
import org.ricone.logging.LogHandler;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.EndpointWebMvcAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.HealthIndicatorAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.ManagementServerPropertiesAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "org.ricone")
@Import({
		EndpointWebMvcAutoConfiguration.class,
		ManagementServerPropertiesAutoConfiguration.class, 
		EndpointAutoConfiguration.class, 
		HealthIndicatorAutoConfiguration.class
		})
@PropertySource("classpath:application.properties")
@EnableWebMvc
@EnableScheduling
@EnableSpringDataWebSupport
public class Config extends WebMvcConfigurerAdapter
{
	@Bean(name="viewProject")
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/xPress/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) 
    {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }

	@Bean
	public AuthHandler getAuthHandler() {
		return new AuthHandler();
	}

	@Override
    public void addInterceptors(InterceptorRegistry registry) 
    {
    	HandlerInterceptor headerHandler = new HeaderHandler();
    	HandlerInterceptor logHandler = new LogHandler();
    	
		registry.addInterceptor(getAuthHandler());
		registry.addInterceptor(headerHandler);
		registry.addInterceptor(logHandler);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		//Override default paging settings, so that an empty paging request is 'unpaged', instead of (0,10)
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		resolver.setOneIndexedParameters(true);
		resolver.setFallbackPageable(Pageable.unpaged());
		argumentResolvers.add(resolver);
		super.addArgumentResolvers(argumentResolvers);
	}
}
