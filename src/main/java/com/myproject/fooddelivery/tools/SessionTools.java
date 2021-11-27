package com.myproject.fooddelivery.tools;

import com.myproject.fooddelivery.code.FoodDeliveryCode;
import com.myproject.fooddelivery.model.UserTable;

import javax.servlet.http.HttpServletRequest;

public class SessionTools {
    public static UserTable getUser(HttpServletRequest servletRequest) {
        UserTable user = (UserTable) servletRequest.getSession().getAttribute(new FoodDeliveryCode().USER_SESSION);
        return user;
    }

    public static void setUser(HttpServletRequest servletRequest,UserTable user){
       servletRequest.getSession().setAttribute(new FoodDeliveryCode().USER_SESSION,user);
    }
}
