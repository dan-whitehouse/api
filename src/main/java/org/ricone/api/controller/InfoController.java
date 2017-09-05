package org.ricone.api.controller;

import org.ricone.api.model.info.*;
import org.ricone.api.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class InfoController
{
    @Autowired
    InfoService infoService;

    @RequestMapping(value= "/info", method = RequestMethod.GET)
    public InfoResponse getSingle(HttpServletResponse response) throws Exception
    {
        Api api = infoService.getAPI();
        Db db = infoService.getDB();
        Config config = infoService.getConfig();

        Info info = new Info();
        info.setApi(api);
        info.setDb(db);
        info.setConfig(config);

        InfoResponse ir = new InfoResponse();
        ir.setInfo(info);
        return ir;
    }
}