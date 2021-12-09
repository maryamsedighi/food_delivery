package com.myproject.fooddelivery.repository;

import com.myproject.fooddelivery.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c where c.userName=?1 and c.password=?2")
    Customer login(String userName, String encryptPassword);

    Customer findCustomersByUserName(String userName);
}
