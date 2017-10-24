package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XStudentMapper;
import org.ricone.api.model.core.Student;
import org.ricone.api.model.xpress.XStudentResponse;
import org.ricone.api.model.xpress.XStudentsResponse;
import org.ricone.api.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xStudents", description = "REST API for xStudents", tags = { "xStudents" })
public class XStudentController extends AbstractController
{
	@Autowired
    IStudentService service;

    @Autowired
    XStudentMapper mapper;


    @ResponseBody
    @ApiOperation(value="Return xStudents by refId", tags = { "xStudents" })
    @RequestMapping(value= "/requests/xStudents/{refId}", method = RequestMethod.GET)
    public XStudentResponse getXStudent(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        Student instance = service.findByRefId(refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStudents", tags = { "xStudents" })
    @RequestMapping(value = "/requests/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudents(HttpServletResponse response, Pageable pageRequest) throws Exception
    {
        List<Student> instance = service.findAll(getPaging(pageRequest));
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xStudents by xLea refId", tags = { "xStudents" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudentsByLea(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        return null;
    }
}