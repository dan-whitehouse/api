package org.ricone.api.xPress.request.xStudent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.AbstractController;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.xPress.model.XStudentResponse;
import org.ricone.api.xPress.model.XStudentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xStudents", description = "REST API for xStudents", tags = {"xStudents"})
public class XStudentController extends AbstractController {
    @Autowired
    private IStudentService service;

    @Autowired
    private XStudentMapper mapper;


    @ResponseBody
    @ApiOperation(value = "Return xStudents by refId", tags = {"xStudents"})
    @RequestMapping(value = "/requests/xStudents/{refId}", method = RequestMethod.GET)
    public XStudentResponse getXStudent(HttpServletResponse response, Pageable pageRequest, @PathVariable(value = "refId") String refId) throws Exception {
        StudentWrapper instance = service.findById(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value = "Return all xStudents", tags = {"xStudents"})
    @RequestMapping(value = "/requests/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudents(HttpServletResponse response, Pageable pageRequest) throws Exception {
        List<StudentWrapper> instance = service.findAll(getMetaData(pageRequest));
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value = "Return all xStudents by xLea refId", tags = {"xStudents"})
    @RequestMapping(value = "/requests/xLeas/{refId}/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudentsByLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value = "refId") String refId) throws Exception {
        List<StudentWrapper> instance = service.findAllByLea(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value = "Return all xStudents by xSchool refId", tags = {"xStudents"})
    @RequestMapping(value = "/requests/xSchools/{refId}/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudentsBySchool(HttpServletResponse response, Pageable pageRequest, @PathVariable(value = "refId") String refId) throws Exception {
        List<StudentWrapper> instance = service.findAllBySchool(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value = "Return all xStudents by xRoster refId", tags = {"xStudents"})
    @RequestMapping(value = "/requests/xRosters/{refId}/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudentsByRoster(HttpServletResponse response, Pageable pageRequest, @PathVariable(value = "refId") String refId) throws Exception {
        List<StudentWrapper> instance = service.findAllByRoster(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value = "Return all xStudents by xStaff refId", tags = {"xStudents"})
    @RequestMapping(value = "/requests/xStaffs/{refId}/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudentsByStaff(HttpServletResponse response, Pageable pageRequest, @PathVariable(value = "refId") String refId) throws Exception {
        List<StudentWrapper> instance = service.findAllByStaff(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value = "Return all xStudents by xContact refId", tags = {"xStudents"})
    @RequestMapping(value = "/requests/xContacts/{refId}/xStudents", method = RequestMethod.GET)
    public XStudentsResponse getXStudentsByContact(HttpServletResponse response, Pageable pageRequest, @PathVariable(value = "refId") String refId) throws Exception {
        List<StudentWrapper> instance = service.findAllByContact(getMetaData(pageRequest), refId);
        return mapper.convert(instance);
    }
}