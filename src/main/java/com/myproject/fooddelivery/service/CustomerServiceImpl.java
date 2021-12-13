package com.myproject.fooddelivery.service;

import com.myproject.fooddelivery.code.ErrorCodes;
import com.myproject.fooddelivery.dto.CustomerDto;
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
    public List<Customer> customerList() {
        List<Customer> allCustomer = customerRepository.findAll();
        return allCustomer;
    }

    @Override
    public void save(CustomerDto customerDto) throws FoodDeliveryException {
        validateSave(customerDto);
        Customer customer = convertFromDtoToCustomer(customerDto);
        customerRepository.save(customer);
    }

    @Override
    public CustomerDto getCustomerDtoById(Integer id) throws FoodDeliveryException {
        Customer customer = getCustomerById(id);
        CustomerDto customerDto = CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .userName(customer.getUserName())
                .password(customer.getPassword())
                .accountStatus(customer.getAccountStatus())
                .build();

        return customerDto;
    }

    private Customer getCustomerById(Integer id) throws FoodDeliveryException {
        Customer customer = customerRepository.findById(id).orElseGet(null);
        if (Objects.isNull(customer)) {
            throw new FoodDeliveryException(ErrorCodes.USER_NOT_FOUND, "the customer is not exists");
        }
        return customer;
    }

    private Customer convertFromDtoToCustomer(CustomerDto customerDto) {

        Customer.CustomerBuilder customerBuilder = Customer.builder().
                firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .phoneNumber(customerDto.getPhoneNumber())
                .address(customerDto.getAddress())
                .userName(customerDto.getUserName())
                .password(customerDto.getPassword())
                .photo(customerDto.getPhoto());
        if (Objects.nonNull(customerDto.getId())) {
            customerBuilder.id(customerDto.getId());
        }
        return customerBuilder.build();

    }

    private void validateSave(CustomerDto customerDto) throws FoodDeliveryException {
        if (Objects.isNull(customerDto)) {
            throw new FoodDeliveryException(ErrorCodes.LOGIN_USER_FORM_IS_NULL, "please fill the form");
        }
        ValidationTools.validateEmptyField(customerDto.getFirstName(), "first name");
        ValidationTools.validateEmptyField(customerDto.getLastName(), "last name");
        ValidationTools.validateEmptyField(customerDto.getEmail(), "email");
        ValidationTools.validateEmptyField(customerDto.getPhoneNumber(), "phone number");
        ValidationTools.validateEmptyField(customerDto.getAddress(), "address");
        Customer customerByUserName = getCustomerByUserName(customerDto.getUserName());
        if (Objects.nonNull(customerByUserName)) {
            throw new FoodDeliveryException(ErrorCodes.CODE_IS_EXISTS, "entry user is exists");
        }
        ValidationTools.validationFieldLength(customerDto.getPassword(), 6, "password");
    }

    private Customer getCustomerByUserName(String userName) {
        return customerRepository.findCustomersByUserName(userName);

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
