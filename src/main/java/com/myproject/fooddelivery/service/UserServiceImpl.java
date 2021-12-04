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

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Long countSubUserOfUser(UserTable userId) {
        Long count = userRepository.countUserTableByUserAdmin(userId);
        return count;
    }

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
                () -> new FoodDeliveryException(ErrorCodes.USER_NOT_FOUND, "the user is not exists")
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

    private UserTable convertFromDtoToUser(UserDto userDto) {
        UserTable.UserTableBuilder userBuilder = UserTable.builder().
                userName(userDto.getUserName())
                .userCode(userDto.getUserCode())
                .contact(userDto.getContact())
                .email(userDto.getEmail())
                .fullName(userDto.getFullName())
                .password(userDto.getPassword())
                .isAdmin(userDto.getIsAdmin());
        if (Objects.nonNull(userDto.getId())) {
            userBuilder.id(userDto.getId());
        }
        return userBuilder.build();

    }

    @Override
    public UserTable getUserById(Integer id) throws FoodDeliveryException {
        UserTable user = userRepository.findById(id).orElseGet(null);
        if (Objects.isNull(user)) {
            throw new FoodDeliveryException(ErrorCodes.USER_NOT_FOUND, "the user is not exists");
        }
        return user;
    }

    @Override
    public UserDto getUserDtoById(Integer id) throws FoodDeliveryException {
        UserTable user = getUserById(id);
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .contact(user.getContact())
                .email(user.getEmail())
                .userName(user.getUserName())
                .userCode(user.getUserCode())
                .password(user.getPassword())
                .isAdmin(user.getIsAdmin())
                .build();

        return userDto;
    }

    @Override
    public void delete(Integer id) throws FoodDeliveryException {
        UserTable userId = getUserById(id);
        validateDelete(userId);
        userRepository.delete(userId);
    }

    @Override
    public HashMap<Integer, String> findAllAdmins() {

        HashMap<Integer, String> admins = new HashMap<>();
        userRepository.findAllAdmins().forEach(user -> admins.put(user.getId(), user.getFullName()));
        return admins;
    }

    private void validateDelete(UserTable userId) throws FoodDeliveryException {
        Long countSubUserOfUser = countSubUserOfUser(userId);
        if (countSubUserOfUser != 0L) {
            throw new FoodDeliveryException(ErrorCodes.USER_IS_ADMIN, "user is admin and can not be delete");
        }
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
        UserTable userByUserCode = getUserByCode(userDto.getUserCode());
        if (Objects.nonNull(userByUserCode)) {
            throw new FoodDeliveryException(ErrorCodes.CODE_IS_EXISTS, "entry code is exists");
        }
        ValidationTools.validationFieldLength(userDto.getPassword(), 6, "password");
    }

    private UserTable getUserByCode(String userCode) {
        return userRepository.findUserTablesByUserCode(userCode);
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
