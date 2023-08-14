package com.CHRESTAPI.todolist.services;

import com.CHRESTAPI.todolist.entities.TodoList;
import com.CHRESTAPI.todolist.repositories.TodoListRepository;
import com.CHRESTAPI.todolist.services.Impl.TodoListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoListServiceTest {

    @Autowired
    TodoListRepository todoListRepository;

    @Autowired
    TodoListServiceImpl todoListService;

      @BeforeEach
    void setUp(){
        todoListRepository = mock(TodoListRepository.class);
        todoListService = new TodoListServiceImpl(todoListRepository);
    }

    @Test
       void ItShouldCheckIfUserIsFoundByName(){
          //given
          String taskName = "Task1";

        TodoList todoList = TodoList.builder().id(1L)
                .name("taskList1")
                .createdDate(LocalDate.of(2023, 7, 27)).build();

        //when
        when(todoListRepository.findListByName(taskName)).thenReturn(Optional.of(todoList));

        Optional<TodoList> result = todoListService.findListByName(taskName);
        System.out.println("result = "+result);
        //then
        assertEquals(todoList,result.get());

    }

    @Test
    void ItShouldCheckIfUserIsFoundById(){
          //given
        Long id = 1L ;

        TodoList todoList = TodoList.builder().id(1L)
                .name("taskList1")
                .createdDate(LocalDate.of(2023, 7, 27)).build();
        //when
        when(todoListRepository.findById(id)).thenReturn(Optional.of(todoList));

        Optional<TodoList> result = todoListService.findListById(id);
        System.out.println("result = "+result);
        //then
        assertEquals(todoList,result.get());

    }
    @Test
    void itShouldCheckIfiTCanFindAllTasks(){


    }



}
