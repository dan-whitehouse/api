package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XStaffMapper;
import org.ricone.api.model.core.Staff;
import org.ricone.api.model.xpress.XRoster;
import org.ricone.api.model.xpress.XStaffResponse;
import org.ricone.api.model.xpress.XStaffsResponse;
import org.ricone.api.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xStaffs", description = "REST API for xStaffs", tags = { "xStaffs" })
public class XStaffController extends AbstractController
{
	@Autowired
    IStaffService service;

    @Autowired
    XStaffMapper mapper;


    @ResponseBody
    @ApiOperation(value="Return xStaff by refId", tags = { "xStaffs" })
    @RequestMapping(value= "/requests/xStaffs/{refId}", method = RequestMethod.GET)
    public XStaffResponse getSingle(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        Staff instance = service.findByRefId(refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xStaffs", tags = { "xStaffs" })
    @RequestMapping(value = "/requests/xStaffs", method = RequestMethod.GET)
    public XStaffsResponse getMulti(HttpServletResponse response) throws Exception
    {
        List<Staff> instance = service.findAll();
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xStaffs by xLea refId", tags = { "xStaffs" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xStaffs", method = RequestMethod.GET)
    public List<XRoster> getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        return null;
    }
}