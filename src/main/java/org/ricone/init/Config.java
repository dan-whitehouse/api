package org.ricone.init;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.ricone.authentication.AuthHandler;
import org.ricone.authentication.HeaderHandler;
import org.ricone.authentication.PermissionHandler;
import org.ricone.logging.LogHandler;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.EndpointWebMvcAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.HealthIndicatorAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.ManagementServerPropertiesAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
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
public class Config implements WebMvcConfigurer
{
	@Bean(name="viewProject")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/xPress/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }

	@Bean
	public AuthHandler getAuthHandler() {
		return new AuthHandler();
	}

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptor permissionHandler = new PermissionHandler();
    	HandlerInterceptor headerHandler = new HeaderHandler();
    	HandlerInterceptor logHandler = new LogHandler();

		registry.addInterceptor(getAuthHandler());
		registry.addInterceptor(permissionHandler);
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
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer
			.favorPathExtension(false)
			.favorParameter(true)
			.parameterName("mediaType")
			.ignoreAcceptHeader(false)
			.ignoreUnknownPathExtensions(true)
			.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML)
			.mediaType("json", MediaType.APPLICATION_JSON);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jsonConverter());
		converters.add(jaxbConverter());
		//TODO Still need to figure out how to handle a 406 (Not Acceptable)
	}

	@Bean
	public MappingJackson2HttpMessageConverter jsonConverter() {
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(String.class, new StringSerializer());

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		mapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
		mapper.disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
		mapper.registerModule(simpleModule);

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(mapper);
		return converter;
	}

	@Bean
	public Jaxb2RootElementHttpMessageConverter jaxbConverter() {
		return new Jaxb2RootElementHttpMessageConverter();
	}
}
