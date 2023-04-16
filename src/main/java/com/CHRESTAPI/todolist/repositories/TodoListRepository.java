package com.CHRESTAPI.todolist.repositories;

import com.CHRESTAPI.todolist.entities.TodoList;
import com.CHRESTAPI.todolist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TodoListRepository extends JpaRepository<TodoList,Long> {

  Optional<List> findListByName(String name);
}
