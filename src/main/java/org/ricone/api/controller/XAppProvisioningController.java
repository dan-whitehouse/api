package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XAppProvisioningMapper;
import org.ricone.api.model.core.UserPassword;
import org.ricone.api.model.xpress.XAppProvisioningResponse;
import org.ricone.api.service.IAppProvisioningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xLeas", description = "REST API for xLeas", tags = { "xLeas" })
public class XAppProvisioningController extends AbstractController
{
	@Autowired
    IAppProvisioningService service;

    @Autowired
    XAppProvisioningMapper mapper;


    /* Staffs */
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value="Create User Accounts for xStaffs by xSchool refId ", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStaffs", method = RequestMethod.POST, params = {"userPasswords"})
    public void provisionXStaffsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean isCreate, @PathVariable(value="refId") String refId) throws Exception
    {
        service.provisionStaffsBySchool(getMetaData(pageRequest), refId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value="Return all provisioned xStaffs by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStaffs", method = RequestMethod.DELETE, params = {"userPasswords"})
    public void deleteXStaffsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean isDelete, @PathVariable(value="refId") String refId) throws Exception
    {
        service.deleteStaffsBySchool(getMetaData(pageRequest), refId);
    }

    @ResponseBody
    @ApiOperation(value="Return all provisioned xStaffs by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStaffs", method = RequestMethod.GET, params = {"userPasswords"})
    public XAppProvisioningResponse getXStaffsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean isGet, @PathVariable(value="refId") String refId) throws Exception
    {
        List<UserPassword> instance = service.findStaffsBySchool(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }


    /* Student */
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value="Create User Accounts for xStudents by xSchool refId ", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStudents", method = RequestMethod.POST, params = {"userPasswords"})
    public void provisionXStudentsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean isCreate, @PathVariable(value="refId") String refId) throws Exception
    {
        service.provisionStudentsBySchool(getMetaData(pageRequest), refId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value="Return all provisioned xStudents by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStudents", method = RequestMethod.DELETE, params = {"userPasswords"})
    public void deleteXStudentsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean isDelete, @PathVariable(value="refId") String refId) throws Exception
    {
        service.deleteStudentsBySchool(getMetaData(pageRequest), refId);
    }

    @ResponseBody
    @ApiOperation(value="Return all provisioned xStudents by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStudents", method = RequestMethod.GET, params = {"userPasswords"})
    public XAppProvisioningResponse getXStudentsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean isGet, @PathVariable(value="refId") String refId) throws Exception
    {
        List<UserPassword> instance = service.findStudentsBySchool(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }
}