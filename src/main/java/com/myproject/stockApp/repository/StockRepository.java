package com.myproject.stockApp.repository;

import com.myproject.stockApp.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<UserTable, Integer> {

    @Query("select u from UserTable u where u.userName=?1 and u.password=?2")
    UserTable login(String userName, String password);

    @Query("select u from UserTable u where u.userAdmin.id=?1")
    List<UserTable> findAllUsersByAdmin(Integer adminId);

    UserTable findUserTablesByUserCode(String userCode);

    Long countUserTableByUserAdmin(UserTable user);

    @Query("select u from UserTable u where u.isAdmin=true")
    List<UserTable> findAllAdmins();
}
