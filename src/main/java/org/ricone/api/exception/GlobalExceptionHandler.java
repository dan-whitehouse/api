package org.ricone.api.exception;

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

    @ExceptionHandler(Exception.class)
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

    @ExceptionHandler(NotFoundException.class)
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
}