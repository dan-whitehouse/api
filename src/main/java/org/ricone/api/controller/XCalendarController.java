package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XCalendarMapper;
import org.ricone.api.mapping.xPress.XSchoolMapper;
import org.ricone.api.model.core.School;
import org.ricone.api.model.core.SchoolCalendar;
import org.ricone.api.model.xpress.*;
import org.ricone.api.service.ICalendarService;
import org.ricone.api.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xCalendars", description = "REST API for xCalendars", tags = { "xCalendars" })
public class XCalendarController extends AbstractController
{
	@Autowired
    ICalendarService service;

    @Autowired
    XCalendarMapper mapper;


    @ResponseBody
    @ApiOperation(value="Return xCalendar by refId", tags = { "xCalendars" })
    @RequestMapping(value= "/requests/xCalendars/{refId}", method = RequestMethod.GET)
    public XCalendarResponse getSingle(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        SchoolCalendar instance = service.findByRefId(refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xCalendars", tags = { "xCalendars" })
    @RequestMapping(value = "/requests/xCalendars", method = RequestMethod.GET)
    public XCalendarsResponse getMulti(HttpServletResponse response) throws Exception
    {
        List<SchoolCalendar> instance = service.findAll();
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xCalendars by xLeas refId", tags = { "xCalendars" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xCalendars", method = RequestMethod.GET)
    public List<XCalendar> getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        return null;
    }
}