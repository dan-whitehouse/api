package org.ricone.api.xPress.request.xContact;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.AbstractController;
import org.ricone.api.core.model.StudentContact;
import org.ricone.api.xPress.model.XContactResponse;
import org.ricone.api.xPress.model.XContactsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xContacts", description = "REST API for xContacts", tags = { "xContacts" })
public class XContactController extends AbstractController
{
	@Autowired
    private IContactService service;

    @Autowired
    private XContactMapper mapper;


    @ResponseBody
    @ApiOperation(value="Return xContacts by refId", tags = { "xContacts" })
    @RequestMapping(value= "/requests/xContacts/{refId}", method = RequestMethod.GET)
    public XContactResponse getXContact(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        StudentContact instance = service.findByRefId(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xContacts", tags = { "xContacts" })
    @RequestMapping(value = "/requests/xContacts", method = RequestMethod.GET)
    public XContactsResponse getXContacts(HttpServletResponse response, Pageable pageRequest) throws Exception
    {
        List<StudentContact> instance = service.findAll(getMetaData(pageRequest));
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xContacts by xLea refId", tags = { "xContacts" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xContacts", method = RequestMethod.GET)
    public XContactsResponse getXContactsByLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<StudentContact> instance = service.findAllByLea(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xContacts by xSchool refId", tags = { "xContacts" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xContacts", method = RequestMethod.GET)
    public XContactsResponse getXContactsBySchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<StudentContact> instance = service.findAllBySchool(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xContacts by xStudent refId", tags = { "xContacts" })
    @RequestMapping(value= "/requests/xStudents/{refId}/xContacts", method = RequestMethod.GET)
    public XContactsResponse getXContactsByStudent(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        List<StudentContact> instance = service.findAllByStudent(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }
}