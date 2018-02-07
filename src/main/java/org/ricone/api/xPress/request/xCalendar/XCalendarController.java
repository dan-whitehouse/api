package org.ricone.api.xPress.request.xCalendar;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.AbstractController;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.xPress.model.XCalendarResponse;
import org.ricone.api.xPress.model.XCalendarsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xCalendars", description = "REST API for xCalendars", tags = {"xCalendars"})
public class XCalendarController extends AbstractController {
    @Autowired
    private ICalendarService service;

    @Autowired
    private XCalendarMapper mapper;


    @ResponseBody
    @ApiOperation(value = "Return xCalendar by refId", tags = {"xCalendars"})
    @RequestMapping(value = "/requests/xCalendars/{refId}", method = RequestMethod.GET)
    public XCalendarResponse getXCalendar(HttpServletResponse response, Pageable pageRequest, @PathVariable(value = "refId") String refId) throws Exception {
        SchoolCalendarWrapper instance = service.findByRefId(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value = "Return all xCalendars", tags = {"xCalendars"})
    @RequestMapping(value = "/requests/xCalendars", method = RequestMethod.GET)
    public XCalendarsResponse getXCalendars(HttpServletResponse response, Pageable pageRequest) throws Exception {
        List<SchoolCalendarWrapper> instance = service.findAll(getMetaData(pageRequest));
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value = "Return all xCalendars by xLea refId", tags = {"xCalendars"})
    @RequestMapping(value = "/requests/xLeas/{refId}/xCalendars", method = RequestMethod.GET)
    public XCalendarsResponse getXCalendarsByLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value = "refId") String refId) throws Exception {
        List<SchoolCalendarWrapper> instance = service.findAllByLea(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value = "Return all xCalendars by xSchool refId", tags = {"xCalendars"})
    @RequestMapping(value = "/requests/xSchools/{refId}/xCalendars", method = RequestMethod.GET)
    public XCalendarsResponse getXCalendarsBySchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value = "refId") String refId) throws Exception {
        List<SchoolCalendarWrapper> instance = service.findAllBySchool(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }
}