package com.myproject.fooddelivery.service;

import com.myproject.fooddelivery.dto.LoginDto;
import com.myproject.fooddelivery.exception.FoodDeliveryException;
import com.myproject.fooddelivery.model.Customer;
import com.myproject.fooddelivery.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mockitoSession;

@DisplayName("test all method in customer service")
@SpringBootTest
@AutoConfigureMockMvc
class CustomerServiceImplTest {

    @Mock
    CustomerServiceImpl customerService;

    @Test
    void login() throws FoodDeliveryException {
//        String firstName = new Customer().getFirstName();
        LoginDto loginDto = new LoginDto();
        loginDto.setUserName("mary");
        loginDto.setPassword("123456");
//        Customer customer = customerService.login(loginDto);
        Mockito.verify(customerService).login(loginDto);
//        assertEquals(customerDto.getFirstName(), firstName);
//        assertAll(() -> assertEquals(customerDto.getId(), 3),
//                () -> assertEquals(customerDto.getFirstName(), "maryam"),
//                () -> assertEquals(customerDto.getAddress(), "sesjh"),
//                () -> assertEquals(customerDto.getPhoneNumber(), "89798"),
//                () -> assertEquals(customerDto.getEmail(), "sadd@gmail.com"),
//                () -> assertEquals(customerDto.getPassword(), 75778),
//                () -> assertEquals(customerDto.getUserName(), "mmm"),
//                () -> assertEquals(customerDto.getAccountStatus(), "ghht")
//        );
    }

    @Test
    void customerList() throws FoodDeliveryException {
//        List listCustomer = mock(List.class);
        List<Customer> listCustomer = customerService.customerList();
        System.out.println("sfdfgdgg    " + listCustomer.contains(0));
        // دستکاری رفتار متد با ماک
       when(listCustomer.size()).thenReturn(0);
//        boolean dff = Mockito.verify(listCustomer, Mockito.times(2)).add("dff");


    }

    @Test
    void save() {
    }

    @Test
    void getCustomerDtoById() throws FoodDeliveryException {
        assertNotNull(customerService.getCustomerDtoById(1));
//        assertNull(customerService.getCustomerDtoById(2));
    }

    //test save method by using file
    @Test
    void importFromFile() {
        try {
            Path path = Paths.get("customer.json");
            if (!Files.exists(path)) {
                Files.createFile(path);
                return;
            }
            List<String> lines = Files.readAllLines(path);

            List<String> success = new ArrayList<>();
            List<String> failed = new ArrayList<>();
//
//            lines.forEach(line->{
//                String[] split = line.split(",");
//                if(split.length != 2) {
//                    failed.add(line);
//                    return;
//                }
//                try {
//                    teamService.save(TeamNewEditDto.builder().name(split[0]).code(split[1]).build());
//                    success.add(split[0]);
//                } catch (LeaveException ignored) {
//                    failed.add(split[0]);
//                }
//            });
        } catch (Exception ignored) {

        }
    }

}