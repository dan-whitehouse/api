package org.ricone.api.controller.xPress;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.controller.AbstractController;
import org.ricone.api.mapping.xPress.XCalendarMapper;
import org.ricone.api.model.core.SchoolCalendar;
import org.ricone.api.model.xpress.XCalendarResponse;
import org.ricone.api.model.xpress.XCalendarsResponse;
import org.ricone.api.service.xPress.ICalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xCalendars", description = "REST API for xCalendars", tags = { "xCalendars" })
public class XCalendarController extends AbstractController
{
	@Autowired
    private ICalendarService service;

    @Autowired
    private XCalendarMapper mapper;


    @ResponseBody
    @ApiOperation(value="Return xCalendar by refId", tags = { "xCalendars" })
    @RequestMapping(value= "/requests/xCalendars/{refId}", method = RequestMethod.GET)
    public XCalendarResponse getXCalendar(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        SchoolCalendar instance = service.findByRefId(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xCalendars", tags = { "xCalendars" })
    @RequestMapping(value = "/requests/xCalendars", method = RequestMethod.GET)
    public XCalendarsResponse getXCalendars(HttpServletResponse response, Pageable pageRequest) throws Exception
    {
        List<SchoolCalendar> instance = service.findAll(getMetaData(pageRequest));
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xCalendars by xLea refId", tags = { "xCalendars" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xCalendars", method = RequestMethod.GET)
    public XCalendarsResponse getXCalendarsByLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<SchoolCalendar> instance = service.findAllByLea(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xCalendars by xSchool refId", tags = { "xCalendars" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xCalendars", method = RequestMethod.GET)
    public XCalendarsResponse getXCalendarsBySchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<SchoolCalendar> instance = service.findAllBySchool(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }
}