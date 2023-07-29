package com.CHRESTAPI.todolist.services;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.repositories.TaskRepository;
import com.CHRESTAPI.todolist.services.Impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp(){
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskServiceImpl(taskRepository);
    }

    @Test
    void testFindByTaskId_ValidId_ReturnTask(){
        //Arrange
        Long taskId = 1L;
        Task task = new Task();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));


        //Act
        Optional<Task> result = taskService.finByTaskId(taskId);
        //Assert
        assertEquals(Optional.of(task),result);
    }




}
