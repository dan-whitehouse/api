package org.ricone.web.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SwaggerController {
    @RequestMapping(value = { "/xPress" }, method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "xPress/index";
    }
}