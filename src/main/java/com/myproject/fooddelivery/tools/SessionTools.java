package com.myproject.fooddelivery.tools;

import com.myproject.fooddelivery.code.FoodDeliveryCode;
import com.myproject.fooddelivery.model.Customer;
import com.myproject.fooddelivery.model.UserTable;

import javax.servlet.http.HttpServletRequest;

public class SessionTools {
    public static UserTable getUser(HttpServletRequest servletRequest) {
        return (UserTable) servletRequest.getSession().getAttribute(new FoodDeliveryCode().USER_SESSION);
    }

    public static Customer getCustomer(HttpServletRequest servletRequest) {
        return (Customer) servletRequest.getSession().getAttribute(new FoodDeliveryCode().USER_SESSION);
    }

    public static void setUser(HttpServletRequest servletRequest,Object user){
       servletRequest.getSession().setAttribute(new FoodDeliveryCode().USER_SESSION,user);
    }
}
