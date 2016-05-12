package org.ricone.api.controller;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface IController<T>
{
	public T getSingle(HttpServletResponse response, String refId) throws Exception;
	public List<T> getMulti(HttpServletResponse response)  throws Exception;
	public List<T> getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception;
}
