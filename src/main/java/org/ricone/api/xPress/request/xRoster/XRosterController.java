package org.ricone.api.xPress.request.xRoster;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.AbstractController;
import org.ricone.api.core.model.CourseSection;
import org.ricone.api.xPress.model.XRosterResponse;
import org.ricone.api.xPress.model.XRostersResponse;
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
    private IRosterService service;

    @Autowired
    private XRosterMapper mapper;

    @ResponseBody
    @ApiOperation(value="Return xRoster by refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xRosters/{refId}", method = RequestMethod.GET)
    public XRosterResponse getXRoster(HttpServletResponse response,  Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        CourseSection instance = service.findByRefId(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters", tags = { "xRosters" })
    @RequestMapping(value = "/requests/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRosters(HttpServletResponse response, Pageable pageRequest) throws Exception
    {
        List<CourseSection> instance = service.findAll(getMetaData(pageRequest));
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters by xLea refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRostersByLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<CourseSection> instance = service.findAllByLea(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters by xSchool refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRostersBySchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<CourseSection> instance = service.findAllBySchool(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters by xCourse refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xCourses/{refId}/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRostersByCourse(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<CourseSection> instance = service.findAllByCourse(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters by xStaff refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xStaffs/{refId}/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRostersByStaff(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<CourseSection> instance = service.findAllByStaff(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters by xStudent refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xStudents/{refId}/xRosters", method = RequestMethod.GET)
    public XRostersResponse getXRostersByStudent(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<CourseSection> instance = service.findAllByStudent(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }
}