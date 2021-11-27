package com.myproject.fooddelivery.controller;

import com.myproject.fooddelivery.tools.SessionTools;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping(path = "/")
    public ModelAndView index(HttpServletRequest servletRequest) {
        if (Objects.isNull(SessionTools.getUser(servletRequest))) {
            return new ModelAndView("redirect:/user/login");
        }
        return new ModelAndView("home/index");
    }

}
