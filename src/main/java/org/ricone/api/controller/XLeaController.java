package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XLeaMapper;
import org.ricone.api.model.core.Lea;

import org.ricone.api.model.xpress.*;
import org.ricone.api.service.LeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "xLeas", description = "REST API for xLeas", tags = { "xLeas" })
public class XLeaController extends AbstractController
{
	@Autowired
	LeaService service;

    @Autowired
	XLeaMapper mapper;


    @ResponseBody
    @ApiOperation(value="Return xLea by refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xLeas/{refId}", method = RequestMethod.GET)
    public XLeaResponse getSingle(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        Lea lea = service.getByRefId(refId);

        XLeaResponse out = new XLeaResponse();
        out.setXLea(mapper.map(lea));
        return out;
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas", tags = { "xLeas" })
    @RequestMapping(value = "/requests/xLeas", method = RequestMethod.GET)
    public XLeasResponse getMulti(HttpServletResponse response) throws Exception
    {
        List<Lea> leas = service.getLeas();

        List<XLea> list = new ArrayList<>();
        for(Lea lea : leas) {
            XLea xLea = mapper.map(lea);
            if (xLea != null) {
                list.add(xLea);
            }
        }

        XLeasResponse xLeasResponse = new XLeasResponse();
        XLeas xLeas = new XLeas();
        xLeas.setXLea(list);
        xLeasResponse.setXLeas(xLeas);
        return xLeasResponse;
    }


    @ResponseBody
    @ApiOperation(value="Return all xLeas by xSchools refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xLeas", method = RequestMethod.GET)
    public List<XLea> getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
    	List<Lea> leas = service.getLeas(); 
        return null;
    }
}