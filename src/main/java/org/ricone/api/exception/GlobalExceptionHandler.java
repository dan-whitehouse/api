package org.ricone.api.exception;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler 
{

	/**** 20X ****/
	@ExceptionHandler(NoContentException.class)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	private @ResponseBody void noContent(HttpServletRequest request, HttpServletResponse response, Exception ex)
	{

	}

	/**** 40X ****/
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	private @ResponseBody Error unauthorized(HttpServletRequest request, HttpServletResponse response, Exception ex)
	{
		Error error = new Error();
		error.setUrl(request.getRequestURL().toString());
		error.setStatus(403);
		error.setMessage("Forbidden");
		error.setDescription(ex.getMessage());
		return error;
	}
	 
    @ExceptionHandler({NoResultException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND) 
    private @ResponseBody Error notFound(HttpServletRequest request, HttpServletResponse response, Exception ex) 
    {
    	Error error = new Error();
    	error.setUrl(request.getRequestURL().toString());
    	error.setStatus(404);
    	error.setMessage("Not Found"); 
        error.setDescription(ex.getMessage());
        return error;
    }
    
    /**** 50X ****/
    @ExceptionHandler({Exception.class, MappingException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) 
    private @ResponseBody Error badRequest(HttpServletRequest request, HttpServletResponse response, Exception ex) 
    {
    	Error error = new Error();
    	error.setUrl(request.getRequestURL().toString());
    	error.setStatus(500);
    	error.setMessage("Internal Server Error"); 
        error.setDescription(ex.getMessage());
        return error;
    }
    
    @ExceptionHandler(ConfigException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE) 
    private @ResponseBody Error configError(HttpServletRequest request, HttpServletResponse response, Exception ex) 
    {
    	Error error = new Error();
    	error.setUrl(request.getRequestURL().toString());
    	error.setStatus(503);
    	error.setMessage("Service Unavailable"); 
        error.setDescription("Configuration Error: " + ex.getMessage());
        return error;
    }
}