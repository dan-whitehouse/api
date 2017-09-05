package org.ricone.api.controller;

import org.ricone.api.model.info.*;
import org.ricone.api.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class SwaggerController
{
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model)
    {
        return "index";
    }
}