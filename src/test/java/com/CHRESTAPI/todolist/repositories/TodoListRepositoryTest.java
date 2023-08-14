package com.CHRESTAPI.todolist.repositories;


import com.CHRESTAPI.todolist.entities.TodoList;
import com.CHRESTAPI.todolist.entities.User;
import com.CHRESTAPI.todolist.enums.Role;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TodoListRepositoryTest {

    @Autowired
    TodoListRepository todoListRepository;

    @AfterEach
     void tearDown(){
        todoListRepository.deleteAll();
    }

    @Test
    @Transactional
    void ItshouldCheckIfListIsFoundByName(){


       //given
        String todoListName = "taskList1";

      /*  User user = User.builder()
                .username("john_doe3")
                .password("password123")
                .email("john1@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .build();*/



        TodoList todoList = TodoList.builder().id(1L)
                .name("taskList1")
                .createdDate(LocalDate.of(2023, 7, 27)).build();

        todoListRepository.save(todoList);
        //when
         Optional<TodoList> result = todoListRepository.findListByName(todoListName);
         //then

        // Check if the Optional contains a value
        assertEquals(true, result.isPresent());
        assertEquals(Optional.of(todoList),result);



             // Using SLF4J to log the expected and actual results
        Logger logger = LoggerFactory.getLogger(TaskRepositoryTest.class);
          logger.info("Expected Result: {}", Optional.of(todoList));
             logger.info("Actual Result: {}", result);


    }

}
