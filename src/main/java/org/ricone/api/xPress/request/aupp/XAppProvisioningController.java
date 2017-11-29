package org.ricone.api.xPress.request.aupp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.AbstractController;
import org.ricone.api.core.model.UserPassword;
import org.ricone.api.xPress.model.XAppProvisioningResponse;
import org.ricone.error.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "xLeas", description = "REST API for xLeas", tags = { "xLeas" })
public class XAppProvisioningController extends AbstractController
{
	@Autowired
    private IAppProvisioningService service;

    @Autowired
    private XAppProvisioningMapper mapper;

    private final String BAD_REQUEST_EXCEPTION = "The request could not be understood by the server due to malformed syntax";

    /* Staffs */
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value="Create User Accounts for xStaffs by xSchool refId ", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStaffs", method = RequestMethod.POST, params = {"userPasswords"})
    public void provisionXStaffsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean userPasswords, @PathVariable(value="refId") String refId) throws Exception
    {
        if(userPasswords) {
            service.provisionStaffsBySchool(getMetaData(pageRequest), refId);
        }
        else throw new BadRequestException(BAD_REQUEST_EXCEPTION);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value="Create User Accounts for xStudents by xSchool refId ", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStudents", method = RequestMethod.POST, params = {"userPasswords"})
    public void provisionXStudentsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean userPasswords, @PathVariable(value="refId") String refId) throws Exception
    {
        if(userPasswords) {
            service.provisionStudentsBySchool(getMetaData(pageRequest), refId);
        }
        else throw new BadRequestException(BAD_REQUEST_EXCEPTION);
    }

    @ResponseBody
    @ApiOperation(value="Return all provisioned xStaffs by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStaffs", method = RequestMethod.GET, params = {"userPasswords"})
    public XAppProvisioningResponse getXStaffsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean userPasswords, @PathVariable(value="refId") String refId) throws Exception
    {
        if(userPasswords) {
            List<UserPassword> instance = service.findStaffsBySchool(getMetaData(pageRequest), refId);
            return mapper.convert(instance);
        }
        else throw new BadRequestException(BAD_REQUEST_EXCEPTION);
    }


    @ResponseBody
    @ApiOperation(value="Return all provisioned xStudents by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStudents", method = RequestMethod.GET, params = {"userPasswords"})
    public XAppProvisioningResponse getXStudentsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean userPasswords, @PathVariable(value="refId") String refId) throws Exception {
        if(userPasswords) {
            List<UserPassword> instance = service.findStudentsBySchool(getMetaData(pageRequest), refId);
            return mapper.convert(instance);
        }
        else throw new BadRequestException(BAD_REQUEST_EXCEPTION);
    }


    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value="Return all provisioned xStaffs by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStaffs", method = RequestMethod.DELETE, params = {"userPasswords"})
    public void deleteXStaffsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean userPasswords, @RequestParam(value="loginIds") Optional<Boolean> loginIds, @PathVariable(value="refId") String refId) throws Exception {
        if(userPasswords) {
            service.deleteStaffsBySchool(getMetaData(pageRequest), refId);
            if(loginIds.isPresent() && loginIds.get())
            {
                service.deleteStaffsLoginIdBySchool(getMetaData(pageRequest), refId);
            }
        }
        else throw new BadRequestException(BAD_REQUEST_EXCEPTION);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value="Return all provisioned xStudents by xSchool refId", tags = { "xAppProvisioning" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xStudents", method = RequestMethod.DELETE, params = {"userPasswords"})
    public void deleteXStudentsBySchool(HttpServletResponse response, Pageable pageRequest, @RequestParam(value="userPasswords") boolean userPasswords, @RequestParam(value="loginIds") Optional<Boolean> loginIds, @PathVariable(value="refId") String refId) throws Exception {
        if(userPasswords) {
            service.deleteStudentsBySchool(getMetaData(pageRequest), refId);
            if(loginIds.isPresent() && loginIds.get())
            {
                service.deleteStudentsLoginIdBySchool(getMetaData(pageRequest), refId);
            }
        }
        else throw new BadRequestException(BAD_REQUEST_EXCEPTION);
    }
}