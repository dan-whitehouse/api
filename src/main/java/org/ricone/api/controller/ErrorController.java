package org.ricone.api.controller;

import org.ricone.api.exception.*;
import org.ricone.api.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@ControllerAdvice
public class ErrorController
{
	/**** 20X ****/
	@ResponseBody
	@ExceptionHandler(NoContentException.class)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void noContent(HttpServletRequest request, HttpServletResponse response, Exception ex)
	{
		//Do nothing
	}

	/**** 40X ****/

	@ResponseBody
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public Error unauthorized(HttpServletRequest request, HttpServletResponse response, Exception ex)
	{
		return error(request.getRequestURL().toString(), 403, "Forbidden", ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler({NoResultException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND) 
    private Error notFound(HttpServletRequest request, HttpServletResponse response, Exception ex)
    {
		return error(request.getRequestURL().toString(), 404, "Not Found", ex.getMessage());
    }
    
    /**** 50X ****/
	@ResponseBody
	@ExceptionHandler({Exception.class, MappingException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) 
    private Error badRequest(HttpServletRequest request, HttpServletResponse response, Exception ex)
    {
		return error(request.getRequestURL().toString(), 500, "Internal Server Error", ex.getMessage());
    }

	@ResponseBody
	@ExceptionHandler(ConfigException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE) 
    private Error configError(HttpServletRequest request, HttpServletResponse response, Exception ex)
    {
        return error(request.getRequestURL().toString(), 503, "Service Unavailable", ex.getMessage());
    }

    private Error error(String url, int status, String message, String description)
	{
		Error error = new Error();
		error.setUrl(url);
		error.setStatus(status);
		error.setMessage(message);
		error.setDescription(description);
		return error;
	}
}