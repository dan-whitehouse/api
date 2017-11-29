package org.ricone.error.exception;

public class ConfigException extends Exception
{
	private static final long serialVersionUID = 1997753363232807009L;

	public ConfigException() 
	{
		super();
	}
	 
	public ConfigException(String message)
	{
		super(message);
	}

	public ConfigException(Throwable cause)
	{
		super(cause);
	}

	public ConfigException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
	 
}