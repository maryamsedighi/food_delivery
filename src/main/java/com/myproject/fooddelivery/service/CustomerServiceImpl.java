package com.myproject.fooddelivery.service;

import com.myproject.fooddelivery.code.ErrorCodes;
import com.myproject.fooddelivery.dto.LoginDto;
import com.myproject.fooddelivery.exception.FoodDeliveryException;
import com.myproject.fooddelivery.model.Customer;
import com.myproject.fooddelivery.repository.CustomerRepository;
import com.myproject.fooddelivery.tools.EncryptTools;
import com.myproject.fooddelivery.tools.ValidationTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer login(LoginDto loginDto) throws FoodDeliveryException {
        validateLogin(loginDto);
        Customer customer = customerRepository.login(loginDto.getUserName(), EncryptTools.encryptPassword(loginDto.getPassword()));
        if (Objects.isNull(customer)) {
            throw new FoodDeliveryException(ErrorCodes.USER_NOT_FOUND, "the user is not exists");
        }
        return customer;
    }

    @Override
    public List<Customer> customerList() throws FoodDeliveryException {
        List<Customer> allCustomer = customerRepository.findAll();
        return allCustomer;
    }

    private void validateLogin(LoginDto loginDto) throws FoodDeliveryException {
        if (Objects.isNull(loginDto)) {
            throw new FoodDeliveryException(ErrorCodes.LOGIN_FORM_IS_NULL, "please fill the form");
        }

        ValidationTools.validateEmptyField(loginDto.getUserName(), "user name");
        ValidationTools.validateEmptyField(loginDto.getPassword(), "password");

        ValidationTools.validationFieldLength(loginDto.getPassword(), 6, "password");
    }
}
