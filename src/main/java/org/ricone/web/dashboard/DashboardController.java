package org.ricone.web.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @project: ricone
 * @author: Dan on 2/6/2018.
 */
@Controller
public class DashboardController {
    @RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
    public String listDocuments(ModelMap model) {
        return "dashboard/index";
    }
}
