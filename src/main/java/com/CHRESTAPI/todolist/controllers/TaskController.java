package com.CHRESTAPI.todolist.controllers;


import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.enums.priority;
import com.CHRESTAPI.todolist.exception.ElementNotFoundException;
import com.CHRESTAPI.todolist.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/findbytasklist")
    public Optional<Task> findById(@PathVariable Long id) {
        try {
            return taskService.finByTaskId(id);
        } catch (ElementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/findbytaskstatus")
    public Optional<Task> findByTaskStatus(@PathVariable String status ){
        try {
            return taskService.findByTaskstatus(status);
        }catch(ElementNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/findbydate")
    public List<Task>  findByDate(@PathVariable LocalDate date){
        try {
            return taskService.findByDate(date);
        }catch(ElementNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }








 //   List<Task> findByDateTimeReminderBetween(LocalDateTime start, LocalDateTime end);

   // List<Task> findByTaskPriority(priority priority);

  //  List<Task> findByCategory(String category);

  //  List<Task> findByTagsIn(Set<String> tags);


}
