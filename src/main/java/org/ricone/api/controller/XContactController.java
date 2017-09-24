package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XContactMapper;
import org.ricone.api.model.core.StudentContact;
import org.ricone.api.model.xpress.XContactResponse;
import org.ricone.api.model.xpress.XContactsResponse;
import org.ricone.api.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xContacts", description = "REST API for xContacts", tags = { "xContacts" })
public class XContactController extends AbstractController
{
	@Autowired
    IContactService service;

    @Autowired
    XContactMapper mapper;


    @ResponseBody
    @ApiOperation(value="Return xContacts by refId", tags = { "xContacts" })
    @RequestMapping(value= "/requests/xContacts/{refId}", method = RequestMethod.GET)
    public XContactResponse getSingle(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        StudentContact instance = service.findByRefId(refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xContacts", tags = { "xContacts" })
    @RequestMapping(value = "/requests/xContacts", method = RequestMethod.GET)
    public XContactsResponse getMulti(HttpServletResponse response) throws Exception
    {
        List<StudentContact> instance = service.findAll();
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xContacts by xLea refId", tags = { "xContacts" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xContacts", method = RequestMethod.GET)
    public XContactsResponse getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        return null;
    }
}