package com.CHRESTAPI.todolist.services;

import com.CHRESTAPI.todolist.entities.TodoList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface TodoListService {

    public Optional<TodoList> findListById(Long id) ;

    public Optional<List> findListByName(String name) ;

    public List<TodoList> findAll();
}
