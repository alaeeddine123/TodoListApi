package com.CHRESTAPI.todolist.dto;

import com.CHRESTAPI.todolist.enums.TaskStatus;
import com.CHRESTAPI.todolist.enums.priority;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

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
}
