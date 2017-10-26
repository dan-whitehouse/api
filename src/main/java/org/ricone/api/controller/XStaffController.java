package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XStaffMapper;
import org.ricone.api.model.core.Staff;
import org.ricone.api.model.xpress.XRoster;
import org.ricone.api.model.xpress.XStaffResponse;
import org.ricone.api.model.xpress.XStaffsResponse;
import org.ricone.api.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xStaffs", description = "REST API for xStaffs", tags = { "xStaffs" })
public class XStaffController extends AbstractController
{
	@Autowired
    IStaffService service;

    @Autowired
    XStaffMapper mapper;


    @ResponseBody
    @ApiOperation(value="Return xStaff by refId", tags = { "xStaffs" })
    @RequestMapping(value= "/requests/xStaffs/{refId}", method = RequestMethod.GET)
    public XStaffResponse getXStaff(HttpServletResponse response, HttpServletRequest request, @PathVariable(value="refId") String refId) throws Exception
    {
        Staff instance = service.findById(request, refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStaffs", tags = { "xStaffs" })
    @RequestMapping(value = "/requests/xStaffs", method = RequestMethod.GET)
    public XStaffsResponse getXStaffs(HttpServletResponse response, Pageable pageRequest) throws Exception
    {
        List<Staff> instance = service.findAll(getPaging(pageRequest));
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xStaffs by xLea refId", tags = { "xStaffs" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xStaffs", method = RequestMethod.GET)
    public XStaffsResponse getXStaffsByLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Staff> instance = service.findAllByLea(getPaging(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStaffs by xSchool refId", tags = { "xStaffs" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStaffs", method = RequestMethod.GET)
    public XStaffsResponse getXStaffsBySchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Staff> instance = service.findAllBySchool(getPaging(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStaffs by xCourse refId", tags = { "xStaffs" })
    @RequestMapping(value= "/requests/xCourses/{refId}/xStaffs", method = RequestMethod.GET)
    public XStaffsResponse getXStaffsByCourse(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Staff> instance = service.findAllByCourse(getPaging(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStaffs by xRoster refId", tags = { "xStaffs" })
    @RequestMapping(value= "/requests/xRosters/{refId}/xStaffs", method = RequestMethod.GET)
    public XStaffsResponse getXStaffsByRoster(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Staff> instance = service.findAllByRoster(getPaging(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStaffs by xStudent refId", tags = { "xStaffs" })
    @RequestMapping(value= "/requests/xStudents/{refId}/xStaffs", method = RequestMethod.GET)
    public XStaffsResponse getXStaffsByStudent(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Staff> instance = service.findAllByStudent(getPaging(pageRequest), refId);
        return mapper.convert(instance);
    }
}