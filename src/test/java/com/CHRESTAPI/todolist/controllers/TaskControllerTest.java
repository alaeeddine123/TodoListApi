package com.CHRESTAPI.todolist.controllers;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.entities.TodoList;
import com.CHRESTAPI.todolist.enums.TaskStatus;
import com.CHRESTAPI.todolist.exception.ElementNotFoundException;
import com.CHRESTAPI.todolist.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.enums.priority;
import com.CHRESTAPI.todolist.exception.ElementNotFoundException;
import com.CHRESTAPI.todolist.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {

        private TaskService taskService;
        private TaskController taskController;

        @BeforeEach
        void setUp() {
            taskService = mock(TaskService.class);
            taskController = new TaskController(taskService);
        }

        @Test
        void testFindByIdSuccess() throws ElementNotFoundException {
            // Arrange
            Long taskId = 11111L;
            Task task = new Task() {{
                setId(11111L);
                setName("Example Task");
                setContent("This is an example task");
                setTaskPriority(priority.HIGH);
                setTaskstatus(TaskStatus.IN_PROGRESS);
                setTime(LocalTime.now());
                setDate(LocalDate.now());
                setCategory("Example Category");
                setTags(new HashSet<>(Arrays.asList("tag1", "tag2", "tag3")));
                setDateTimeReminder(LocalDateTime.now());
                setTaskList(new TodoList() {{ setName("Example TodoList"); }});
            }};

            when(taskService.finByTaskId(taskId)).thenReturn(Optional.of(task));

            // Act
            Optional<Task> result = taskController.findById(taskId);

            // Assert
            assertTrue(result.isPresent());
            assertEquals(taskId, result.get().getId());
        }

        @Test
        void testFindByIdNotFound() throws ElementNotFoundException {
            // Arrange
            Long taskId = 1L;
            when(taskService.finByTaskId(taskId)).thenThrow(new ElementNotFoundException("Task not found"));

            // Act & Assert
            ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
                taskController.findById(taskId);
            });
            assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
            assertEquals("Task not found", exception.getReason());
        }

        // Add more test cases for the remaining methods in TaskController class
    }