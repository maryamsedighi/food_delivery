package com.myproject.stockApp.controller;

import com.myproject.stockApp.code.StockCode;
import com.myproject.stockApp.dto.LoginDto;
import com.myproject.stockApp.dto.UserDto;
import com.myproject.stockApp.exception.StockAppException;
import com.myproject.stockApp.model.UserTable;
import com.myproject.stockApp.service.StockService;
import com.myproject.stockApp.tools.SessionTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/stock")
public class WareHouseController {

    @Autowired
    StockService stockService;

    @GetMapping(path = "/login")
    public ModelAndView login(HttpServletRequest servletRequest) {
        return new ModelAndView("user/login", new HashMap() {{
            put("login", new LoginDto());
        }});
    }

    @PostMapping(path = "/login")
    public ModelAndView login(@ModelAttribute("login") LoginDto loginDto, HttpServletRequest servletRequest) {
        try {
            UserTable user = stockService.login(loginDto);
            SessionTools.setUser(servletRequest, user);
        } catch (StockAppException e) {
            return new ModelAndView("core/error", new HashMap() {{
                put("code", e.getCode());
                put("message", e.getMessage());
            }});
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping(path = "/logout")
    public ModelAndView logout(HttpServletRequest servletRequest) {
        servletRequest.removeAttribute(StockCode.USER_SESSION);
        return new ModelAndView("redirect:/user/login");
    }

    @GetMapping(path = "/list")
    public ModelAndView userList(HttpServletRequest servletRequest) {
        if (Objects.isNull(SessionTools.getUser(servletRequest))) {
            return new ModelAndView("redirect:/user/login");
        }
        try {
            List<UserTable> userList = stockService.listSubUsers(SessionTools.getUser(servletRequest).getId());
            return new ModelAndView("user/listAll", new HashMap() {{
                put("userList", userList);
            }}
            );
        } catch (StockAppException e) {
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
        HashMap<Integer, String> admins = stockService.findAllAdmins();
        return new ModelAndView("user/newEdit", new HashMap() {{
            put("admins", admins);
            put("user", userDto);
        }});
    }

    @PostMapping(path = "/add")
    public ModelAndView add(@ModelAttribute("user") UserDto userDto, HttpServletRequest servletRequest) {
        if (Objects.isNull(SessionTools.getUser(servletRequest))) {
            return new ModelAndView("redirect:/user/login");
        }
        try {
            stockService.save(userDto);
            return new ModelAndView("redirect:/user/list");
        } catch (StockAppException e) {
            return new ModelAndView("core/error", new HashMap() {{
                put("code", e.getCode());
                put("message", e.getMessage());
            }});
        }
    }

    @GetMapping(path = "/edit/{id}")
    public ModelAndView editUser(@PathVariable("id") Integer id, HttpServletRequest servletRequest) {
        if (Objects.isNull(SessionTools.getUser(servletRequest))) {
            return new ModelAndView("redirect:/user/login");
        }
        try {
            UserDto user = stockService.getUserDtoById(id);
            return new ModelAndView("user/newEdit", new HashMap() {{
                put("user", user);
            }});
        } catch (StockAppException e) {
            return new ModelAndView("core/error", new HashMap() {{
                put("code", e.getCode());
                put("message", e.getMessage());
            }});
        }
    }

    @GetMapping(path = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id, HttpServletRequest servletRequest) {
        if (Objects.isNull(SessionTools.getUser(servletRequest))) {
            return new ModelAndView("redirect:/user/login");
        }
        try {
            stockService.delete(id);
            return new ModelAndView("redirect:/user/list");
        } catch (StockAppException e) {
            return new ModelAndView("core/error", new HashMap() {{
                put("code", e.getCode());
                put("message", e.getMessage());
            }});
        }
    }

}
