package com.myproject.stockApp.service;

import com.myproject.stockApp.code.ErrorCodes;
import com.myproject.stockApp.dto.LoginDto;
import com.myproject.stockApp.dto.UserDto;
import com.myproject.stockApp.exception.StockAppException;
import com.myproject.stockApp.model.UserTable;
import com.myproject.stockApp.repository.StockRepository;
import com.myproject.stockApp.tools.EncryptTools;
import com.myproject.stockApp.tools.ValidationTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    @Override
    public Long countSubUserOfUser(UserTable userId) {
        Long count = stockRepository.countUserTableByUserAdmin(userId);
        return count;
    }

    @Override
    public UserTable login(LoginDto loginDto) throws StockAppException {
        validateLogin(loginDto);
        UserTable user = stockRepository.login(loginDto.getUserName(), EncryptTools.encryptPassword(loginDto.getPassword()));
        if (Objects.isNull(user)) {
            throw new StockAppException(ErrorCodes.USER_NOT_FOUND, "the user is not exists");
        }
        return user;
    }

    @Override
    public List<UserTable> listSubUsers(Integer userId) throws StockAppException {
        UserTable user = stockRepository.findById(userId).orElseThrow(
                () -> new StockAppException(ErrorCodes.USER_NOT_FOUND, "the user is not exists")
        );
        List<UserTable> allUsers = stockRepository.findAllUsersByAdmin(userId);
        return allUsers;
    }

    @Override
    public void save(UserDto userDto) throws StockAppException {
        validateSave(userDto);
        UserTable user = convertFromDtoToUser(userDto);
        try {
            stockRepository.save(user);
        } catch (Exception e) {
            new StockAppException("there are some error in database", ErrorCodes.DATABASE_ERROR);
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
    public UserTable getUserById(Integer id) throws StockAppException {
        UserTable user = stockRepository.findById(id).orElseGet(null);
        if (Objects.isNull(user)) {
            throw new StockAppException(ErrorCodes.USER_NOT_FOUND, "the user is not exists");
        }
        return user;
    }

    @Override
    public UserDto getUserDtoById(Integer id) throws StockAppException {
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
    public void delete(Integer id) throws StockAppException {
        UserTable userId = getUserById(id);
        validateDelete(userId);
        stockRepository.delete(userId);
    }

    @Override
    public HashMap<Integer, String> findAllAdmins() {

        HashMap<Integer, String> admins = new HashMap<>();
        stockRepository.findAllAdmins().forEach(user -> admins.put(user.getId(), user.getFullName()));
        return admins;
    }

    private void validateDelete(UserTable userId) throws StockAppException {
        Long countSubUserOfUser = countSubUserOfUser(userId);
        if (countSubUserOfUser != 0L) {
            throw new StockAppException(ErrorCodes.USER_IS_ADMIN, "user is admin and can not be delete");
        }
    }

    private void validateSave(UserDto userDto) throws StockAppException {
        if (Objects.isNull(userDto)) {
            throw new StockAppException(ErrorCodes.LOGIN_USER_FORM_IS_NULL, "please fill the form");
        }
        ValidationTools.validateEmptyField(userDto.getUserName(), "user name");
        ValidationTools.validateEmptyField(userDto.getPassword(), "password");
        ValidationTools.validateEmptyField(userDto.getFullName(), "name");
        ValidationTools.validateEmptyField(userDto.getEmail(), "email");
        ValidationTools.validateEmptyField(userDto.getContact(), "contact");
        UserTable userByUserCode = getUserByCode(userDto.getUserCode());
        if (Objects.nonNull(userByUserCode)) {
            throw new StockAppException(ErrorCodes.CODE_IS_EXISTS, "entry code is exists");
        }
        ValidationTools.validationFieldLength(userDto.getPassword(), 6, "password");
    }

    private UserTable getUserByCode(String userCode) {
        return stockRepository.findUserTablesByUserCode(userCode);
    }

    private void validateLogin(LoginDto loginDto) throws StockAppException {
        if (Objects.isNull(loginDto)) {
            throw new StockAppException(ErrorCodes.LOGIN_FORM_IS_NULL, "please fill the form");
        }

        ValidationTools.validateEmptyField(loginDto.getUserName(), "user name");
        ValidationTools.validateEmptyField(loginDto.getPassword(), "password");

        ValidationTools.validationFieldLength(loginDto.getPassword(), 6, "password");


    }
}
