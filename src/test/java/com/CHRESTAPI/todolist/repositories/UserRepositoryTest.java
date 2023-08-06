package com.CHRESTAPI.todolist.repositories;

import com.CHRESTAPI.todolist.entities.User;
import com.CHRESTAPI.todolist.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserRepositoryTest {


    @Autowired

    private  UserRepository underTest;


    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void    itShouldCheckIfUserIsfoundByEmail() {
        //given
        String email = "john@example.com";
        User user = User.builder()
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .build();
        underTest.save(user);
        //when
        Optional<User> result = underTest.findByEmail(email);
        //then
        assertEquals(Optional.of(user),result);
    }

    @Test
    void    itShouldCheckIfUserIsNotfoundByEmail() {
        //given
        String email = "john@example.com";
        //when
        Optional<User> result = underTest.findByEmail(email);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void itShouldCheckIfUserIsFoundByUsernameOrEmail() {
    //given
        String email = "john2@example.com";
        String username = "john_doe2";
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
        User user = User.builder()
                .username("john_doe2")
                .password("password123")
                .email("john2@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .build();
        System.out.println("Created user: " + user);

        underTest.save(user);
        //when
        Optional<User> result = underTest.findByUsernameOrEmail(email,username);
        System.out.println("Result: " + result);

        //then

       assertEquals(Optional.of(user),result);
    }

    @Test
    void    itShouldCheckIfUserIsNotfoundByUsernameOrEmail() {
        //given
        String email = "john@example.com";
        String username = "john_doe2";

        //when
         Optional<User> result = underTest.findByUsernameOrEmail(email,username);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void itShouldCheckIfUserIsFoundByUsernameThatContainsKeyword() {

       //given
        String keyword  = "manage";
        System.out.println("keyword to test  = "+keyword);
        User user =  User.builder().username("manager1")
                .password("password123")
                .email("john2@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .build();
        System.out.println("Created user: " + user);
        underTest.save(user);
        //when
        Optional<User> result = underTest.findByUsernameContains(keyword);
        //then
        assertEquals(Optional.of(user),result);
    }

     @Test
    void    itShouldCheckIfUserIsNotFoundByUsernameThatContainsKeyword() {
        //given
         String keyword  = "manage";

        //when
        Optional<User> result = underTest.findByUsernameContains(keyword);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void itShouldCheckIfUserIsFoundByUsername()  {
        //given

        String username = "john_doe3";
        System.out.println("Username: " + username);
        User user = User.builder()
                .username("john_doe3")
                .password("password123")
                .email("john2@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .build();
        System.out.println("Created user: " + user);

        underTest.save(user);
        //when
        Optional<User> result = underTest.findByUsername(username);
        System.out.println("Result: " + result);

        //then

       assertEquals(Optional.of(user),result);



    }

     @Test
    void    itShouldCheckIfUserIsNotFoundByUsername() {
        //given
          String username = "john_doe3";
        //when
          Optional<User> result = underTest.findByUsername(username);
           System.out.println("Result: " + result);
        //then
        assertFalse(result.isPresent());
    }

}