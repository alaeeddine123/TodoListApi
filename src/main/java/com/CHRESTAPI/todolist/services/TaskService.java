package com.CHRESTAPI.todolist.services;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.enums.priority;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public interface TaskService {

    Optional<Task> finByTaskId(Long id);

    List<Task> findByTaskList(String taskList);

    List<Task> findByTaskstatus(String status);

    List<Task> findByDate(LocalDate date);

    List<Task> findByDateTimeReminderBetween(LocalDateTime start, LocalDateTime end);

    List<Task> findByTaskPriority(priority priority);

    List<Task> findByCategory(String category);

    List<Task> findByTagsIn(Set<String> tags);
}

