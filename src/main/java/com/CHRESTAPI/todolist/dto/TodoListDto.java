package com.CHRESTAPI.todolist.dto;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.entities.TodoList;
import com.CHRESTAPI.todolist.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoListDto {


    private Long id;
    private String name;
    private LocalDate createdDate;
    private User user;
    private List<Task> tasks = new ArrayList<>();



    public static TodoListDto fromEntity(TodoList todoList) {
        TodoListDto dto = new TodoListDto();
        dto.setId(todoList.getId());
        dto.setName(todoList.getName());
        dto.setCreatedDate(todoList.getCreatedDate());
        // Convert tasks to TaskDTOs
        dto.setTasks(todoList.getTasks());


        return dto;
    }

    public TodoList toEntity(TodoListDto todoListDto) {
        TodoList todoList = new TodoList();
        todoList.setId(todoListDto.getId());
        todoList.setName(todoListDto.getName());
        todoList.setCreatedDate(todoListDto.getCreatedDate());
        // Convert TaskDTOs to tasks
        todoList.setTasks(todoListDto.getTasks());
        return todoList;
    }

}
