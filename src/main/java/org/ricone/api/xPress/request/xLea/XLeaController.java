package org.ricone.api.xPress.request.xLea;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.AbstractController;
import org.ricone.api.core.model.Lea;
import org.ricone.api.xPress.model.XLeaResponse;
import org.ricone.api.xPress.model.XLeasResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xLeas", description = "REST API for xLeas", tags = { "xLeas" })
public class XLeaController extends AbstractController
{
	@Autowired
    private ILeaService service;

    @Autowired
    private XLeaMapper mapper;

    @ResponseBody
    @ApiOperation(value="Return xLea by refId or localId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xLeas/{refId}", method = RequestMethod.GET)
    public XLeaResponse getXLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        Lea instance = service.findById(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas", tags = { "xLeas" })
    @RequestMapping(value = "/requests/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeas(HttpServletResponse response, Pageable pageRequest) throws Exception {

        List<Lea> instance = service.findAll(getMetaData(pageRequest));
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xSchool refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasBySchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllBySchool(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xCalendar refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xCalendars/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasByCalendar(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllByCalendar(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xCourse refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xCourses/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasByCourse(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllByCourse(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xRoster refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xRosters/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasByRoster(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllByRoster(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xStaff refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xStaffs/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasByStaff(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllByStaff(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xStudent refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xStudents/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasByStudent(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllByStudent(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xContact refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xContacts/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasByContact(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllByContact(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }
}