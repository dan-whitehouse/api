package org.ricone.api.xPress.request.changesSince;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.AbstractController;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xPress.model.XChangesSinceResponse;
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
    private IChangesSinceService service;

    @Autowired
    private XEventLogMapper mapper;

    @ResponseBody
    @ApiOperation(value="Return all xLeas which have change extension", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xLeas", method = RequestMethod.GET , params = {"changesSinceMarker"})
    //GET http://localhost:8080/api/requests/xLeas?changesSinceMarker=2000-01-31T00:00:00.000-00:00
    public XChangesSinceResponse getXChangesSinceByLea(HttpServletResponse response, Pageable pageRequest, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime changesSinceMarker) throws Exception
    {
        List<EventLogWrapper> instance = service.findAllByLea(getMetaData(pageRequest), changesSinceMarker);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xSchools which have change extension", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xSchools", method = RequestMethod.GET, params = {"changesSinceMarker"})
    public XChangesSinceResponse getXChangesSinceBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime changesSinceMarker) throws Exception
    {
        List<EventLogWrapper> instance = service.findAllBySchool(getMetaData(pageRequest), changesSinceMarker);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xCalendars which have change extension", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xCalendars", method = RequestMethod.GET, params = {"changesSinceMarker"})
    public XChangesSinceResponse getXChangesSinceByCalendar(HttpServletResponse response, Pageable pageRequest, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime changesSinceMarker) throws Exception
    {
        List<EventLogWrapper> instance = service.findAllByCalendar(getMetaData(pageRequest), changesSinceMarker);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xCourses which have change extension", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xCourses", method = RequestMethod.GET, params = {"changesSinceMarker"})
    public XChangesSinceResponse getXChangesSinceByCourse(HttpServletResponse response, Pageable pageRequest, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime changesSinceMarker) throws Exception
    {
        List<EventLogWrapper> instance = service.findAllByCourse(getMetaData(pageRequest), changesSinceMarker);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xRosters which have change extension", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xRosters", method = RequestMethod.GET, params = {"changesSinceMarker"})
    public XChangesSinceResponse getXChangesSinceByRoster(HttpServletResponse response, Pageable pageRequest, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime changesSinceMarker) throws Exception
    {
        List<EventLogWrapper> instance = service.findAllByRoster(getMetaData(pageRequest), changesSinceMarker);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStaffs which have change extension", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xStaffs", method = RequestMethod.GET, params = {"changesSinceMarker"})
    public XChangesSinceResponse getXChangesSinceByStaff(HttpServletResponse response, Pageable pageRequest, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime changesSinceMarker) throws Exception
    {
        List<EventLogWrapper> instance = service.findAllByStaff(getMetaData(pageRequest), changesSinceMarker);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStudents which have change extension", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xStudents", method = RequestMethod.GET, params = {"changesSinceMarker"})
    public XChangesSinceResponse getXChangesSinceByStudent(HttpServletResponse response, Pageable pageRequest, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime changesSinceMarker) throws Exception
    {
        List<EventLogWrapper> instance = service.findAllByStudent(getMetaData(pageRequest), changesSinceMarker);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xContacts which have change extension", tags = { "xChangesSince" })
    @RequestMapping(value= "/requests/xContacts", method = RequestMethod.GET, params = {"changesSinceMarker"})
    public XChangesSinceResponse getXChangesSinceByContact(HttpServletResponse response, Pageable pageRequest, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime changesSinceMarker) throws Exception
    {
        //TODO - Not returning anything, even after setting the iso year to 2000 when I know there are values in the db with year values of 2016
        List<EventLogWrapper> instance = service.findAllByContact(getMetaData(pageRequest), changesSinceMarker);
        return mapper.convert(instance);
    }
}