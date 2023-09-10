package com.CHRESTAPI.todolist.services.Impl;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.entities.TodoList;
import com.CHRESTAPI.todolist.enums.Priority;
import com.CHRESTAPI.todolist.enums.TaskStatus;
import com.CHRESTAPI.todolist.exception.ElementNotFoundException;
import com.CHRESTAPI.todolist.repositories.TaskRepository;
import com.CHRESTAPI.todolist.services.TaskService;
import lombok.extern.log4j.Log4j;
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

        System.out.println(" Id recieved as input in service method findby task id : "+id);
        if(id == null || id <= 0){
            throw  new  IllegalArgumentException("Invalid task id");
        }
        return Optional.ofNullable(taskRepository
            .findById(id).orElseThrow(()-> new ElementNotFoundException("Task not found")));


    }

    @Override
    public Optional<Task> findByTaskList(TodoList taskList) {
        if(taskList == null ){
            throw   new IllegalArgumentException("Task list null or empty");
        }
        return taskRepository.findByTaskList(taskList);
    }

    @Override
    public Optional<Task> findByTaskstatus(TaskStatus status) {
        return taskRepository.findByTaskstatus(status);
    }

    @Override
    public Optional<Task> findByDate(LocalDate date) {
        return taskRepository.findByDate(date);
    }

    @Override
    public Optional<Task> findByDateTimeReminderBetween(LocalDateTime start, LocalDateTime end) {
        return  taskRepository.findByDateTimeReminderBetween(start,end);
    }

    @Override
    public Optional<Task> findByTaskPriority(Priority priority) {
        return taskRepository.findByTaskPriority(priority);
    }

    @Override
    public Optional<Task> findByCategory(String category) {
        return taskRepository.findByCategory(category);
    }

    @Override
    public Optional<Task> findByTagsIn(Set<String> tags) {
        return taskRepository.findByTagsIn(tags);
    }



    @Override
    public Task save(Task task) {
     taskRepository.save(task);
        return task;
    }
}

