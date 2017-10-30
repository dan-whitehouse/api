package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XStaffMapper;
import org.ricone.api.mapping.xPress.XStudentMapper;
import org.ricone.api.model.core.Staff;
import org.ricone.api.model.core.Student;
import org.ricone.api.model.xpress.XStaffsResponse;
import org.ricone.api.model.xpress.XStudentsResponse;
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
    XStaffMapper xStaffMapper;

    @Autowired
    XStudentMapper xStudentMapper;

    /* Staffs */
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value="Create User Accounts for xStaffs by xSchool refId ", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStaffs", method = RequestMethod.POST, params = {"createUsers"})
    public void provisionXStaffsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam boolean isCreate, @PathVariable(value="refId") String refId) throws Exception
    {
        boolean isProvisioned = service.provisionStaffsBySchool(getPaging(pageRequest), refId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value="Return all provisioned xStaffs by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStaffs", method = RequestMethod.GET, params = {"deleteUsers"})
    public void deleteXStaffsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam boolean isDelete, @PathVariable(value="refId") String refId) throws Exception
    {
        boolean isDeleted = service.deleteStaffsBySchool(getPaging(pageRequest), refId);
    }

    @ResponseBody
    @ApiOperation(value="Return all provisioned xStaffs by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStaffs", method = RequestMethod.GET, params = {"getUsers"})
    public XStaffsResponse getXStaffsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam boolean isGet, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Staff> instance = service.findStaffsBySchool(getPaging(pageRequest), refId);
        return xStaffMapper.convert(instance);
    }


    /* Student */
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value="Create User Accounts for xStudents by xSchool refId ", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStudents", method = RequestMethod.POST, params = {"createUsers"})
    public void provisionXStudentsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam boolean isCreate, @PathVariable(value="refId") String refId) throws Exception
    {
        boolean isProvisioned = service.provisionStudentsBySchool(getPaging(pageRequest), refId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value="Return all provisioned xStudents by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStudents", method = RequestMethod.GET, params = {"deleteUsers"})
    public void deleteXStudentsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam boolean isDelete, @PathVariable(value="refId") String refId) throws Exception
    {
        boolean isDeleted = service.deleteStudentsBySchool(getPaging(pageRequest), refId);
    }

    @ResponseBody
    @ApiOperation(value="Return all provisioned xStudents by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStudents", method = RequestMethod.GET, params = {"getUsers"})
    public XStudentsResponse getXStudentsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam boolean isGet, @PathVariable(value="refId") String refId) throws Exception
    {
        List<Student> instance = service.findStudentsBySchool(getPaging(pageRequest), refId);
        return xStudentMapper.convert(instance);
    }
}