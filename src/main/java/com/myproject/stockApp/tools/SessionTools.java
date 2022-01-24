package com.myproject.stockApp.tools;

import com.myproject.stockApp.code.StockCode;
import com.myproject.stockApp.model.Customer;
import com.myproject.stockApp.model.UserTable;

import javax.servlet.http.HttpServletRequest;

public class SessionTools {
    public static UserTable getUser(HttpServletRequest servletRequest) {
        UserTable user = (UserTable) servletRequest.getSession().getAttribute(new StockCode().USER_SESSION);
        return user;
    }

    public static Customer getCustomer(HttpServletRequest servletRequest) {
        Customer customer = (Customer) servletRequest.getSession().getAttribute(new StockCode().USER_SESSION);
        return customer;
    }

    public static void setUser(HttpServletRequest servletRequest,Object user){
       servletRequest.getSession().setAttribute(new StockCode().USER_SESSION,user);
    }
}
