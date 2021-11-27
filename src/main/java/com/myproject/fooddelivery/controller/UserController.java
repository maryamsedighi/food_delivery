package com.myproject.fooddelivery.controller;

import com.myproject.fooddelivery.code.FoodDeliveryCode;
import com.myproject.fooddelivery.dto.LoginDto;
import com.myproject.fooddelivery.dto.UserDto;
import com.myproject.fooddelivery.exception.FoodDeliveryException;
import com.myproject.fooddelivery.model.UserTable;
import com.myproject.fooddelivery.service.UserService;
import com.myproject.fooddelivery.tools.SessionTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/login")
    public ModelAndView login(HttpServletRequest servletRequest) {
        return new ModelAndView("user/login", new HashMap() {{
            put("login", new LoginDto());
        }});
    }

    @PostMapping(path = "/login")
    public ModelAndView login(@ModelAttribute("login") LoginDto loginDto, HttpServletRequest servletRequest) {
        try {
            UserTable user = userService.login(loginDto);
            SessionTools.setUser(servletRequest, user);
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
        return new ModelAndView("redirect:/user/login");
    }

    @GetMapping(path = "/list")
    public ModelAndView userList(HttpServletRequest servletRequest) {
        if (Objects.isNull(SessionTools.getUser(servletRequest))) {
            return new ModelAndView("redirect:/user/login");
        }
        try {
            List<UserTable> userList = userService.listSubUsers(SessionTools.getUser(servletRequest).getId());
            return new ModelAndView("user/listAll", new HashMap() {{
                put("userList", userList);
            }}
            );
        } catch (FoodDeliveryException e) {
            return new ModelAndView("core/error", new HashMap() {{
                put("code", e.getCode());
                put("message", e.getMessage());
            }});
        }
    }

    @GetMapping(path = "/add")
    public ModelAndView add(HttpServletRequest servletRequest) {
        if (Objects.isNull(SessionTools.getUser(servletRequest))) {
            return new ModelAndView("redirect:/user/login");
        }
        UserDto userDto = new UserDto();
        userDto.setUserAdminId(SessionTools.getUser(servletRequest).getId());
        return new ModelAndView("user/newEdit", new HashMap() {{
            put("user", userDto);
        }});
    }

    @PostMapping(path = "/add")
    public ModelAndView add(@ModelAttribute("user") UserDto userDto, HttpServletRequest servletRequest) {
        if (Objects.isNull(SessionTools.getUser(servletRequest))) {
            return new ModelAndView("redirect:/user/login");
        }
        try {
            userService.save(userDto);
            return new ModelAndView("redirect:/user/list");
        } catch (FoodDeliveryException e) {
            return new ModelAndView("core/error", new HashMap() {{
                put("code", e.getCode());
                put("message", e.getMessage());
            }});
        }
    }
}
