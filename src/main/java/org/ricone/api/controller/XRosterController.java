package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XCourseMapper;
import org.ricone.api.mapping.xPress.XRosterMapper;
import org.ricone.api.model.core.Course;
import org.ricone.api.model.core.CourseSection;
import org.ricone.api.model.xpress.*;
import org.ricone.api.service.ICourseService;
import org.ricone.api.service.IRosterService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public XRosterResponse getSingle(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        CourseSection instance = service.findByRefId(refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters", tags = { "xRosters" })
    @RequestMapping(value = "/requests/xRosters", method = RequestMethod.GET)
    public XRostersResponse getMulti(HttpServletResponse response) throws Exception
    {
        List<CourseSection> instance = service.findAll();
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xRosters by xLea refId", tags = { "xRosters" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xRosters", method = RequestMethod.GET)
    public List<XRoster> getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        return null;
    }
}