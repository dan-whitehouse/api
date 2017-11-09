package org.ricone.api.xPress.request.xSchool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.AbstractController;
import org.ricone.api.core.dao.School;
import org.ricone.api.xPress.model.XSchoolResponse;
import org.ricone.api.xPress.model.XSchoolsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xSchools", description = "REST API for xSchools", tags = { "xSchools" })
public class XSchoolController extends AbstractController
{
	@Autowired
    private ISchoolService service;

    @Autowired
    private XSchoolMapper mapper;


    @ResponseBody
    @ApiOperation(value="Return xSchool by refId, localId, or BEDS", tags = { "xSchools" })
    @RequestMapping(value= "/requests/xSchools/{refId}", method = RequestMethod.GET)
    public XSchoolResponse getXSchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        School instance = service.findById(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xSchools", tags = { "xSchools" })
    @RequestMapping(value = "/requests/xSchools", method = RequestMethod.GET)
    public XSchoolsResponse getXSchools(HttpServletResponse response, Pageable pageRequest) throws Exception
    {
        List<School> instance = service.findAll(getMetaData(pageRequest));
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xSchools by xLea refId", tags = { "xSchools" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xSchools", method = RequestMethod.GET)
    public XSchoolsResponse getXSchoolsByLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<School> instance = service.findAllByLea(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xSchools by xCalendar refId", tags = { "xSchools" })
    @RequestMapping(value= "/requests/xCalendars/{refId}/xSchools", method = RequestMethod.GET)
    public XSchoolsResponse getXSchoolsByCalendar(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<School> instance = service.findAllByCalendar(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xSchools by xCourse refId", tags = { "xSchools" })
    @RequestMapping(value= "/requests/xCourses/{refId}/xSchools", method = RequestMethod.GET)
    public XSchoolsResponse getXSchoolsByCourse(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<School> instance = service.findAllByCourse(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xSchools by xRoster refId", tags = { "xSchools" })
    @RequestMapping(value= "/requests/xRosters/{refId}/xSchools", method = RequestMethod.GET)
    public XSchoolsResponse getXSchoolsByRoster(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<School> instance = service.findAllByRoster(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xSchools by xStaff refId", tags = { "xSchools" })
    @RequestMapping(value= "/requests/xStaffs/{refId}/xSchools", method = RequestMethod.GET)
    public XSchoolsResponse getXSchoolsByStaff(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<School> instance = service.findAllByStaff(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xSchools by xStudent refId", tags = { "xSchools" })
    @RequestMapping(value= "/requests/xStudents/{refId}/xSchools", method = RequestMethod.GET)
    public XSchoolsResponse getXSchoolsByStudent(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<School> instance = service.findAllByStudent(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xSchools by xContact refId", tags = { "xSchools" })
    @RequestMapping(value= "/requests/xContacts/{refId}/xSchools", method = RequestMethod.GET)
    public XSchoolsResponse getXSchoolsByContact(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<School> instance = service.findAllByContact(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }
}