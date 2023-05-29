package com.CHRESTAPI.todolist.controllers;


import com.CHRESTAPI.todolist.dto.TodoListDto;
import com.CHRESTAPI.todolist.services.TodoListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/tasklist")
public class TodoListController {


    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }


    @GetMapping("/findtasklists")
    public ResponseEntity<List<TodoListDto>> getAllTaskLists(){
    return null;    }
}
