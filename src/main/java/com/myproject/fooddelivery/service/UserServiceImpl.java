package com.myproject.fooddelivery.service;

import com.myproject.fooddelivery.code.ErrorCodes;
import com.myproject.fooddelivery.dto.LoginDto;
import com.myproject.fooddelivery.dto.UserDto;
import com.myproject.fooddelivery.exception.FoodDeliveryException;
import com.myproject.fooddelivery.model.UserTable;
import com.myproject.fooddelivery.repository.UserRepository;
import com.myproject.fooddelivery.tools.EncryptTools;
import com.myproject.fooddelivery.tools.ValidationTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserTable login(LoginDto loginDto) throws FoodDeliveryException {
        validateLogin(loginDto);
        UserTable user = userRepository.login(loginDto.getUserName(), EncryptTools.encryptPassword(loginDto.getPassword()));
        if (Objects.isNull(user)) {
            throw new FoodDeliveryException(ErrorCodes.USER_NOT_FOUND, "the user is not exists");
        }
        return user;
    }

    @Override
    public List<UserTable> listSubUsers(Integer userId) throws FoodDeliveryException {
        UserTable user = userRepository.findById(userId).orElseThrow(
                () -> new FoodDeliveryException(ErrorCodes.USER_NOT_FOUND, "the user not exist")
        );
        List<UserTable> allUsers = userRepository.findAllUsersByAdmin(userId);
        return allUsers;
    }

    @Override
    public void save(UserDto userDto) throws FoodDeliveryException {
        validateSave(userDto);
        UserTable user = convertFromDtoToUser(userDto);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            new FoodDeliveryException("there are some error in database", ErrorCodes.DATABASE_ERROR);
        }

    }

    private UserTable convertFromDtoToUser(UserDto userDto) throws FoodDeliveryException {
        UserTable user = new UserTable();
        user.setFullName(userDto.getFullName());
        user.setContact(userDto.getContact());
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setPassword(EncryptTools.encryptPassword(userDto.getPassword()));
        user.setIsAdmin(userDto.getIsAdmin());
        if (Objects.nonNull(userDto.getUserAdminId())) {
            user.setUserAdmin(getUserById(userDto.getUserAdminId()));
        }
        return user;

    }

    @Override
    public UserTable getUserById(Integer userAdminId) {
        UserTable user = userRepository.findById(userAdminId).orElseGet(null);
        return user;
    }

    private void validateSave(UserDto userDto) throws FoodDeliveryException {
        if (Objects.isNull(userDto)) {
            throw new FoodDeliveryException(ErrorCodes.LOGIN_USER_FORM_IS_NULL, "please fill the form");
        }
        ValidationTools.validateEmptyField(userDto.getUserName(), "user name");
        ValidationTools.validateEmptyField(userDto.getPassword(), "password");
        ValidationTools.validateEmptyField(userDto.getFullName(), "name");
        ValidationTools.validateEmptyField(userDto.getEmail(), "email");
        ValidationTools.validateEmptyField(userDto.getContact(), "contact");

        ValidationTools.validationFieldLength(userDto.getPassword(), 6, "password");
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
