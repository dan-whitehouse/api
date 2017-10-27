package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XChangesSinceMapper;
import org.ricone.api.model.core.EventLog;
import org.ricone.api.model.xpress.XChangesSinceResponse;
import org.ricone.api.service.IChangesSinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Api(value = "xChangesSince", description = "REST API for xChangesSince", tags = { "xChangesSince" })
public class XChangesSinceController extends AbstractController
{
	@Autowired
    IChangesSinceService service;

    @Autowired
    XChangesSinceMapper mapper;

    @ResponseBody
    @ApiOperation(value="Return all xLeas which have change events", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xChangesSince/{iso8601}/xLeas", method = RequestMethod.GET)
    public XChangesSinceResponse getXChangesSinceByLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="iso8601") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime iso8601) throws Exception
    {
        List<EventLog> instance = service.findAllByLea(getPaging(pageRequest), iso8601);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xSchools which have change events", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xChangesSince/{iso8601}/xSchools", method = RequestMethod.GET)
    public XChangesSinceResponse getXChangesSinceBySchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="iso8601") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime iso8601) throws Exception
    {
        List<EventLog> instance = service.findAllBySchool(getPaging(pageRequest), iso8601);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xCalendars which have change events", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xChangesSince/{iso8601}/xCalendars", method = RequestMethod.GET)
    public XChangesSinceResponse getXChangesSinceByCalendar(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="iso8601") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime iso8601) throws Exception
    {
        List<EventLog> instance = service.findAllByCalendar(getPaging(pageRequest), iso8601);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xCourses which have change events", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xChangesSince/{iso8601}/xSCourses", method = RequestMethod.GET)
    public XChangesSinceResponse getXChangesSinceByCourse(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="iso8601") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime iso8601) throws Exception
    {
        List<EventLog> instance = service.findAllByCourse(getPaging(pageRequest), iso8601);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters which have change events", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xChangesSince/{iso8601}/xRosters", method = RequestMethod.GET)
    public XChangesSinceResponse getXChangesSinceByRoster(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="iso8601") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime iso8601) throws Exception
    {
        List<EventLog> instance = service.findAllByRoster(getPaging(pageRequest), iso8601);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStaffs which have change events", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xChangesSince/{iso8601}/xStaffs", method = RequestMethod.GET)
    public XChangesSinceResponse getXChangesSinceByStaff(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="iso8601") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime iso8601) throws Exception
    {
        List<EventLog> instance = service.findAllByStaff(getPaging(pageRequest), iso8601);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStudents which have change events", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xChangesSince/{iso8601}/xStudents", method = RequestMethod.GET)
    public XChangesSinceResponse getXChangesSinceByStudent(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="iso8601") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime iso8601) throws Exception
    {
        List<EventLog> instance = service.findAllByStudent(getPaging(pageRequest), iso8601);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xContacts which have change events", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xChangesSince/{iso8601}/xContacts", method = RequestMethod.GET)
    public XChangesSinceResponse getXChangesSinceByContact(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="iso8601") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime iso8601) throws Exception
    {
        List<EventLog> instance = service.findAllByContact(getPaging(pageRequest), iso8601);
        return mapper.convert(instance);
    }
}