package com.CHRESTAPI.todolist.dto;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.entities.User;
import com.CHRESTAPI.todolist.enums.TaskStatus;
import com.CHRESTAPI.todolist.enums.priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String name ;
    private String content;
    @Enumerated(EnumType.STRING)
    private priority taskPriority;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskstatus;
    private LocalTime time;
    private LocalDate date;
    private String category;
    @ElementCollection
    private Set<String> tags;
    private LocalDateTime dateTimeReminder;


    public static TaskDto fromEntity(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setContent(task.getContent());
        taskDto.setTaskPriority(task.getTaskPriority());
        taskDto.setCategory(task.getCategory());
        taskDto.setDate(task.getDate());
        taskDto.setDateTimeReminder(task.getDateTimeReminder());
        taskDto.setTags(task.getTags());
        taskDto.setTaskstatus(task.getTaskstatus());
        taskDto.setTime(task.getTime());

        return taskDto;

    }



    public  static  Task toEntity(TaskDto taskDto){
        if (taskDto == null) return null;

              Task task = new Task();
              task.setId(taskDto.getId());
              task.setName(taskDto.getName());
              task.setContent(taskDto.getContent());
              task.setTaskPriority(taskDto.getTaskPriority());
              task.setCategory(taskDto.getCategory());
              task.setDate(taskDto.getDate());
              task.setDateTimeReminder(taskDto.getDateTimeReminder());
              task.setTags(taskDto.getTags());
              task.setTaskstatus(taskDto.getTaskstatus());
              task.setTime(taskDto.getTime());

              return  task;
                    }
}