package com.CHRESTAPI.todolist.entities;


import com.CHRESTAPI.todolist.enums.TaskStatus;
import com.CHRESTAPI.todolist.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name ;
    private String content;
    @Enumerated(EnumType.STRING)
    private Priority taskPriority;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskstatus;
    private LocalTime time;
    private LocalDate date;
    private String category;
    @ElementCollection
    private Set<String> tags;
    private LocalDateTime dateTimeReminder;

    @ManyToOne(fetch = FetchType.EAGER)
    private TodoList taskList;

}
