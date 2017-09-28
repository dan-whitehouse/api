package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.exception.MappingException;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.mapping.xPress.XLeaMapper;
import org.ricone.api.model.core.Lea;

import org.ricone.api.model.xpress.*;
import org.ricone.api.service.ILeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@Api(value = "xLeas", description = "REST API for xLeas", tags = { "xLeas" })
public class XLeaController extends AbstractController
{
	@Autowired
    ILeaService service;

    @Autowired
	XLeaMapper mapper;

    @ResponseBody
    @ApiOperation(value="Return xLea by refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xLeas/{refId}", method = RequestMethod.GET)
    public XLeaResponse getXLea(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        Lea instance = service.findById(refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas", tags = { "xLeas" })
    @RequestMapping(value = "/requests/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeas(HttpServletResponse response, Pageable pageRequest) throws Exception
    {
        List<Lea> instance = service.findAll(pageRequest);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xSchool refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasBySchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllBySchool(pageRequest, refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xCalendar refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xCalendars/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasByCalendar(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllByCalendar(pageRequest, refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xRoster refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xRosters/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasByRoster(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllByRoster(pageRequest, refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xStaff refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xStaffs/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasByStaff(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllByStaff(pageRequest, refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xStudent refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xStudents/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasByStudent(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllByStudent(pageRequest, refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas by xContact refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xContacts/{refId}/xLeas", method = RequestMethod.GET)
    public XLeasResponse getXLeasByContact(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Lea> instance = service.findAllByContact(pageRequest, refId);
        return mapper.convert(instance);
    }
}