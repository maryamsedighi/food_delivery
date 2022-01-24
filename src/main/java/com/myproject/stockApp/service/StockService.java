package com.myproject.stockApp.service;

import com.myproject.stockApp.dto.LoginDto;
import com.myproject.stockApp.dto.UserDto;
import com.myproject.stockApp.exception.StockAppException;
import com.myproject.stockApp.model.UserTable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface StockService {

    Long countSubUserOfUser(UserTable userId);

    UserTable login(LoginDto loginDto) throws StockAppException;

    List<UserTable> listSubUsers(Integer userId) throws StockAppException;

    void save(UserDto userDto) throws StockAppException;

    UserTable getUserById(Integer id) throws StockAppException;

    UserDto getUserDtoById(Integer id) throws StockAppException;

    void delete(Integer id) throws StockAppException;

    HashMap<Integer, String> findAllAdmins();
}
