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
		//Do nothing
	}

	/**** 40X ****/
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	private @ResponseBody Error unauthorized(HttpServletRequest request, HttpServletResponse response, Exception ex)
	{
		return error(request.getRequestURL().toString(), 403, "Forbidden", ex.getMessage());
	}

    @ExceptionHandler({NoResultException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND) 
    private @ResponseBody Error notFound(HttpServletRequest request, HttpServletResponse response, Exception ex) 
    {
		return error(request.getRequestURL().toString(), 404, "Not Found", ex.getMessage());
    }
    
    /**** 50X ****/
    @ExceptionHandler({Exception.class, MappingException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) 
    private @ResponseBody Error badRequest(HttpServletRequest request, HttpServletResponse response, Exception ex) 
    {
		return error(request.getRequestURL().toString(), 500, "Internal Server Error", ex.getMessage());
    }
    
    @ExceptionHandler(ConfigException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE) 
    private @ResponseBody Error configError(HttpServletRequest request, HttpServletResponse response, Exception ex) 
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