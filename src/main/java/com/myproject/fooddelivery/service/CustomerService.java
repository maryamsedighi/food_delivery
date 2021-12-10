package com.myproject.fooddelivery.service;

import com.myproject.fooddelivery.dto.CustomerDto;
import com.myproject.fooddelivery.dto.LoginDto;
import com.myproject.fooddelivery.exception.FoodDeliveryException;
import com.myproject.fooddelivery.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    Customer login(LoginDto loginDto) throws FoodDeliveryException;

    List<Customer> customerList() throws FoodDeliveryException;

    void save(CustomerDto customerDto) throws FoodDeliveryException;

    CustomerDto getCustomerDtoById(Integer id) throws FoodDeliveryException;
}
