package org.ricone.api.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;

public class Paging
{
	private Integer pageNum;
	private Integer pageSize;

	private static final String pageNumR = "PageNumber";
	private static final String pageSizeR = "PageSize";

	public Paging(HttpServletRequest request) {
		final boolean isHeader = StringUtils.isNotBlank(request.getHeader("Authorization"));
		final boolean isParameter = StringUtils.isNotBlank(request.getParameter("access_token"));

		if(isHeader)
		{
			setPageNum(NumberUtils.createInteger(request.getHeader(pageNumR)));
			setPageSize(NumberUtils.createInteger(request.getHeader(pageSizeR)));
		}
		else if(isParameter)
		{
			setPageNum(NumberUtils.createInteger(request.getParameter(pageNumR)));
			setPageSize(NumberUtils.createInteger(request.getParameter(pageSizeR)));
		}
	}

	public Integer getPageNum()
	{
		return pageNum;
	}
	public void setPageNum(Integer pageNum)
	{
		this.pageNum = pageNum;
	}
	public Integer getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "Paging{" +
				"pageNum=" + pageNum +
				", pageSize=" + pageSize +
				'}';
	}
}
