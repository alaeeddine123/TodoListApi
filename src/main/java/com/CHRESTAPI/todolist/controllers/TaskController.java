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

import javax.naming.ServiceUnavailableException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@Slf4j
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);



    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/findbytasklist")
    public Task findById(@PathVariable Long id) {
        return taskService.finByTaskId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with ID: " + id));
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

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/findbydatetimereminderbetween")
    public List<Task> finByDateTimeReminderBetween(@PathVariable LocalDateTime start, LocalDateTime end) {
        try {
            List<Task> tasks = taskService.findByDateTimeReminderBetween(start, end);
            if (tasks.isEmpty()) {
                throw new ElementNotFoundException("Element not found");
            }
            return tasks;
        } catch (ElementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid arguments", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }




    public List<Task> findByTaskPriority(@PathVariable priority priority){
        try {
            List<Task> tasks = taskService.findByTaskPriority(priority);
            if (tasks.isEmpty()) {
                throw new ElementNotFoundException("Element not found");
            }

    }catch (ElementNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);:wq:
        }

   // List<Task> findByTaskPriority(priority priority);

  //  List<Task> findByCategory(String category);

  //  List<Task> findByTagsIn(Set<String> tags);


}
