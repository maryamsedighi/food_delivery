package com.myproject.fooddelivery.controller;

import com.myproject.fooddelivery.code.FoodDeliveryCode;
import com.myproject.fooddelivery.dto.LoginDto;
import com.myproject.fooddelivery.exception.FoodDeliveryException;
import com.myproject.fooddelivery.model.Customer;
import com.myproject.fooddelivery.service.CustomerService;
import com.myproject.fooddelivery.tools.SessionTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(path = "/login")
    public ModelAndView login(HttpServletRequest servletRequest) {
        return new ModelAndView("customer/login", new HashMap() {{
            put("login", new LoginDto());
        }});
    }

    @PostMapping(path = "/login")
    public ModelAndView login(@ModelAttribute("login") LoginDto loginDto, HttpServletRequest servletRequest) {
        try {
            Customer customer = customerService.login(loginDto);
            SessionTools.setUser(servletRequest, customer);
        } catch (FoodDeliveryException e) {
            return new ModelAndView("core/error", new HashMap() {{
                put("code", e.getCode());
                put("message", e.getMessage());
            }});
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping(path = "/logout")
    public ModelAndView logout(HttpServletRequest servletRequest) {
        servletRequest.removeAttribute(FoodDeliveryCode.USER_SESSION);
        return new ModelAndView("redirect:/customer/login");
    }

    @GetMapping(path = "/list")
    public ModelAndView customerList(HttpServletRequest servletRequest) {
        if (Objects.isNull(SessionTools.getUser(servletRequest))) {
            return new ModelAndView("redirect:/customer/login");
        }
        try {
            List<Customer> customerList = customerService.customerList();
            return new ModelAndView("customer/listAll", new HashMap() {{
                put("customerList", customerList);
            }}
            );
        } catch (FoodDeliveryException e) {
            return new ModelAndView("core/error", new HashMap() {{
                put("code", e.getCode());
                put("message", e.getMessage());
            }});
        }
    }

}
