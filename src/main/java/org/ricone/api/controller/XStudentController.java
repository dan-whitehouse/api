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
    public XStudentResponse getXStudent(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        Student instance = service.findById(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStudents", tags = { "xStudents" })
    @RequestMapping(value = "/requests/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudents(HttpServletResponse response, Pageable pageRequest) throws Exception
    {
        List<Student> instance = service.findAll(getMetaData(pageRequest));
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xStudents by xLea refId", tags = { "xStudents" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudentsByLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Student> instance = service.findAllByLea(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStudents by xSchool refId", tags = { "xStudents" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudentsBySchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Student> instance = service.findAllBySchool(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStudents by xRoster refId", tags = { "xStudents" })
    @RequestMapping(value= "/requests/xRosters/{refId}/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudentsByRoster(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Student> instance = service.findAllByRoster(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStudents by xStaff refId", tags = { "xStudents" })
    @RequestMapping(value= "/requests/xStaffs/{refId}/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudentsByStaff(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Student> instance = service.findAllByStaff(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStudents by xContact refId", tags = { "xStudents" })
    @RequestMapping(value= "/requests/xContacts/{refId}/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudentsByContact(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Student> instance = service.findAllByContact(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }
}