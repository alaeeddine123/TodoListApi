package com.CHRESTAPI.todolist.services.Impl;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.enums.priority;
import com.CHRESTAPI.todolist.repositories.TaskRepository;
import com.CHRESTAPI.todolist.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class TaskServiceImpl  implements TaskService {



    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> finByTaskId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findByTaskList(String taskList) {
        return taskRepository.findByTaskList(taskList);
    }

    @Override
    public Optional<Task> findByTaskstatus(String status) {
        return taskRepository.findByTaskstatus(status);
    }

    @Override
    public List<Task> findByDate(LocalDate date) {
        return taskRepository.findByDate(date);
    }

    @Override
    public List<Task> findByDateTimeReminderBetween(LocalDateTime start, LocalDateTime end) {
        return  taskRepository.findByDateTimeReminderBetween(start,end);
    }

    @Override
    public List<Task> findByTaskPriority(priority priority) {
        return taskRepository.findByTaskPriority(priority);
    }

    @Override
    public List<Task> findByCategory(String category) {
        return taskRepository.findByCategory(category);
    }

    @Override
    public List<Task> findByTagsIn(Set<String> tags) {
        return taskRepository.findByTagsIn(tags);
    }
}
