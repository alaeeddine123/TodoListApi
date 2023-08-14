package com.CHRESTAPI.todolist.services.Impl;

import com.CHRESTAPI.todolist.entities.TodoList;
import com.CHRESTAPI.todolist.repositories.TodoListRepository;
import com.CHRESTAPI.todolist.services.TodoListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;

    public TodoListServiceImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    public Optional<TodoList> findListById(Long id) {
        return todoListRepository.findById(id);
    }

    @Override
    public Optional<TodoList> findListByName(String name) {
        return todoListRepository.findListByName(name) ;
    }

    @Override
    public List<TodoList> findAll() {
        return todoListRepository.findAll();
    }
}
