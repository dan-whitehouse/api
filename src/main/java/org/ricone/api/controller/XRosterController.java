package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XCourseMapper;
import org.ricone.api.mapping.xPress.XRosterMapper;
import org.ricone.api.model.core.Course;
import org.ricone.api.model.core.CourseSection;
import org.ricone.api.model.core.Lea;
import org.ricone.api.model.xpress.*;
import org.ricone.api.service.ICourseService;
import org.ricone.api.service.IRosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xRosters", description = "REST API for xRosters", tags = { "xRosters" })
public class XRosterController extends AbstractController
{
	@Autowired
    IRosterService service;

    @Autowired
    XRosterMapper mapper;

    @ResponseBody
    @ApiOperation(value="Return xRoster by refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xRosters/{refId}", method = RequestMethod.GET)
    public XRosterResponse getXRoster(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        CourseSection instance = service.findByRefId(refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters", tags = { "xRosters" })
    @RequestMapping(value = "/requests/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRosters(HttpServletResponse response, Pageable pageRequest) throws Exception
    {
        List<CourseSection> instance = service.findAll(getPaging(pageRequest));
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters by xLea refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRostersByLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<CourseSection> instance = service.findAllByLea(getPaging(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters by xSchool refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRostersBySchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<CourseSection> instance = service.findAllBySchool(getPaging(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters by xCourse refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xCourses/{refId}/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRostersByCourse(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<CourseSection> instance = service.findAllByCourse(getPaging(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters by xStaff refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xStaffs/{refId}/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRostersByStaff(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<CourseSection> instance = service.findAllByStaff(getPaging(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters by xStudent refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xStudents/{refId}/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRostersByStudent(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<CourseSection> instance = service.findAllByStudent(getPaging(pageRequest), refId);
        return mapper.convert(instance);
    }
}