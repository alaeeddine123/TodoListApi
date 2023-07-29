package com.CHRESTAPI.todolist.services;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.entities.TodoList;
import com.CHRESTAPI.todolist.enums.Priority;
import com.CHRESTAPI.todolist.enums.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public interface TaskService {

    Optional<Task> finByTaskId(Long id);

    Optional<Task> findByTaskList(TodoList taskList);

    Optional <Task> findByTaskstatus(TaskStatus status);

    Optional<Task> findByDate(LocalDate date);

    List<Task> findByDateTimeReminderBetween(LocalDateTime start, LocalDateTime end);

    List<Task> findByTaskPriority(Priority priority);

    List<Task> findByCategory(String category);

    List<Task> findByTagsIn(Set<String> tags);

    Task save(Task task);
}

