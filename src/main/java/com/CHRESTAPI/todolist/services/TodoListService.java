package com.CHRESTAPI.todolist.services;

import com.CHRESTAPI.todolist.entities.TodoList;
import com.CHRESTAPI.todolist.repositories.TodoListRepository;

import java.util.List;
import java.util.Optional;

public interface TodoListService {

    public Optional<TodoList> findListById(Long id) ;

    public Optional<TodoList> findListByName(String name) ;

    public List<TodoList> findAll();
}
