package org.ricone.api.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() 
	{
		return new Class[] { Config.class, Configure.class};
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() 
	{
		return null;
	}
 
	@Override
	protected String[] getServletMappings() 
	{
		return new String[] { "/" };
	}
}
