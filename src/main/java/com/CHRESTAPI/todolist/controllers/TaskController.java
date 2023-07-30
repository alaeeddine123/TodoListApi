package com.CHRESTAPI.todolist.controllers;


import com.CHRESTAPI.todolist.dto.TaskDto;
import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.enums.Priority;
import com.CHRESTAPI.todolist.enums.TaskStatus;
import com.CHRESTAPI.todolist.errors.ErrorResponse;
import com.CHRESTAPI.todolist.exception.ElementNotFoundException;
import com.CHRESTAPI.todolist.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/findbytaskid/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        return taskService.finByTaskId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with ID: " + id));
    }

    @GetMapping("/findbytaskstatus/{status}")
    public Optional<Task> findByTaskStatus(@PathVariable TaskStatus status) {

        try {
            return taskService.findByTaskstatus(status);
        } catch (ElementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/findbydate/{date}")
    public Optional<Task> findByDate(@PathVariable LocalDate date) {
        try {
            return taskService.findByDate(date);
        } catch (ElementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/findbydatetimereminderbetween/{start}/{end}")
    public Optional<Task> finByDateTimeReminderBetween(@PathVariable LocalDateTime start, LocalDateTime end) {
        try {
            Optional<Task> tasks = taskService.findByDateTimeReminderBetween(start, end);
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

    @GetMapping("/findbytaskpriority/{priority}")
    public Optional<Task> findByTaskPriority(@PathVariable Priority priority) {
        try {
            Optional<Task> tasks = taskService.findByTaskPriority(priority);
            if (tasks.isEmpty()) {
                throw new ElementNotFoundException("Element not found");
            }
        return tasks;
        } catch (ElementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
    @GetMapping("/findbycategory/{category}")
    public Optional<Task> findbycategory(@PathVariable String category){
        try{
            Optional<Task> tasks = taskService.findByCategory(category);
            if (tasks.isEmpty()){
                throw  new ElementNotFoundException("element not found");
            }
            return tasks;
        }catch (ElementNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @GetMapping("/findbytagsin")
    public Optional<Task> findByTagsIn(@PathVariable Set<String> tags){
        try{
            Optional<Task> tasks = taskService.findByTagsIn(tags);
            if (tasks.isEmpty()) throw new ElementNotFoundException("element not found");
            return tasks;
        }catch (ElementNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
    }
    @PostMapping("/createtask")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        Task createdTask = taskService.save(TaskDto.toEntity(taskDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto.fromEntity(createdTask));
    }

    @PostMapping("/updatetask/{taskId}")
    public ResponseEntity<TaskDto> editTask(@PathVariable Long taskId, @RequestBody TaskDto updatedTaskDto){
        Optional<Task> optionalTask = taskService.finByTaskId(taskId);
        if(optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setName(updatedTaskDto.getName());
            task.setContent(updatedTaskDto.getContent());
            task.setTaskstatus(updatedTaskDto.getTaskstatus());
            task.setTaskPriority(updatedTaskDto.getTaskPriority());
            task.setDate(updatedTaskDto.getDate());
            task.setTime(updatedTaskDto.getTime());
            task.setDateTimeReminder(updatedTaskDto.getDateTimeReminder());

            Task savedTask = taskService.save(TaskDto.toEntity(updatedTaskDto));
            TaskDto updatedTaskResponse = TaskDto.fromEntity (savedTask);
            return ResponseEntity.ok(updatedTaskResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Task not found with ID: " + taskId);
        }
    }



    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleElementNotFoundException(ElementNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerError(Exception ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");
    }


}



