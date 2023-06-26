package com.CHRESTAPI.todolist.services.Impl;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.enums.Priority;
import com.CHRESTAPI.todolist.exception.ElementNotFoundException;
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

        if(id == null || id <= 0){
            throw  new  IllegalArgumentException("Invalid task id");
        }
        return Optional.ofNullable(taskRepository
            .findById(id).orElseThrow(()-> new ElementNotFoundException("Task not found")));


    }

    @Override
    public List<Task> findByTaskList(String taskList) {
        if(taskList == null || taskList.isEmpty()){
            throw   new IllegalArgumentException("Task list null or empty");
        }
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
    public List<Task> findByTaskPriority(Priority priority) {
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



    @Override
    public Task save(Task task) {
     taskRepository.save(task);
        return task;
    }
}

