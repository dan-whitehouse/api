package org.ricone.api.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HeaderHandler extends HandlerInterceptorAdapter 
{
	private static final String pageNum = "pageNum";
	private static final String pageSize = "pageSize";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{	
		response.setStatus(response.getStatus());
		response.setHeader("ServicePath", request.getRequestURI());
		setPaging(request, response);
			
		return super.preHandle(request, response, handler);
	}
	
	private void setPaging(HttpServletRequest request, HttpServletResponse response)
	{
		boolean pagingNumHeader = StringUtils.isNotBlank(request.getHeader(pageNum));
		boolean pagingSizeHeader = StringUtils.isNotBlank(request.getHeader(pageSize));
		boolean pagingNumParam = StringUtils.isNotBlank(request.getParameter(pageNum));
		boolean pagingSizeParam = StringUtils.isNotBlank(request.getParameter(pageSize));
		
		if(pagingNumHeader && pagingSizeHeader)
		{
			response.setHeader("PageNumber", request.getHeader(pageNum));
			response.setHeader("PageSize", request.getHeader(pageSize));
		}
		else if(pagingNumParam && pagingSizeParam)
		{
			response.setHeader("PageNumber", request.getParameter(pageNum));
			response.setHeader("PageSize", request.getParameter(pageSize));
		}
	}
}