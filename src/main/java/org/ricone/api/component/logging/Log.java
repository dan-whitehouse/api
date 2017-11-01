package org.ricone.api.component.logging;

import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Log 
{
	private int status;
	private long duration;
	private Level level;
	
	public Log(HttpServletRequest request, HttpServletResponse response) 
	{
		this.level = getLogLevel(response);
		this.status = response.getStatus();
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	private Level getLogLevel(HttpServletResponse response) 
	{		
		if(response.getStatus() <= 200)
		{
			return Level.INFO;
		}
		if(response.getStatus() >= 300 && response.getStatus() <= 500)
		{
			return Level.WARN;
		}
		else if(response.getStatus() >= 500)
		{
			return Level.ERROR;
		}
		else
		{
			return Level.FATAL;
		}
	}
	
	
	@Override
	public String toString() 
	{
		return "Log [status=" + status + "] + [duration=" + duration + "]";
	}

	
	
	
	

}
