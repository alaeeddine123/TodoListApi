package com.CHRESTAPI.todolist.controllers;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.entities.TodoList;
import com.CHRESTAPI.todolist.enums.TaskStatus;
import com.CHRESTAPI.todolist.exception.ElementNotFoundException;
import com.CHRESTAPI.todolist.services.TaskService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import com.CHRESTAPI.todolist.enums.Priority;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.BDDMockito.given;
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
        void ItShouldCheckIfTaskIsFoundById() throws ElementNotFoundException {
            // Arrange
            Long taskId = 1L;
            Task task = new Task() {{
                setId(1L);
                setName("Example Task");
                setContent("This is an example task");
                setTaskPriority(Priority.HIGH);
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
            Optional<ResponseEntity<Task>> result = Optional.ofNullable(taskController.findById(taskId));

            // Assert
            assertTrue(result.isPresent());
            assertEquals(result.get().getBody(),task);
            System.out.println("result is "+result.get().getBody());
            System.out.println("tested task object :"+task );
        }
        @Test
        void  itShouldCheckIfTaskIsNotFoundById() throws ElementNotFoundException {
            // Arrange
            Long taskId = 1L;
            when(taskService.finByTaskId(taskId)).thenThrow(new ElementNotFoundException("Task not found"));

            // Act & Assert
            ElementNotFoundException  exception = assertThrows(ElementNotFoundException.class, () -> {
                taskController.findById(taskId);
            });
            assertEquals("Task not found", exception.getMessage());
            System.out.println(exception.getMessage());

        }
        @Test
        void itShouldCheckIfTaskIsFoundByDate(){
            //given
            LocalDate taskDate = LocalDate.of(2023, 7, 27);
            Task task = new Task() {{
                setId(1L);
                setName("Example Task");
                setContent("This is an example task");
                setTaskPriority(Priority.HIGH);
                setTaskstatus(TaskStatus.IN_PROGRESS);
                setTime(LocalTime.now());
                setDate(LocalDate.now());
                setCategory("Example Category");
                setTags(new HashSet<>(Arrays.asList("tag1", "tag2", "tag3")));
                setDateTimeReminder(LocalDateTime.now());
                setTaskList(new TodoList() {{ setName("Example TodoList"); }});
            }};
            //when
            when(taskService.findByDate(taskDate)).thenReturn(Optional.of(task));
            Optional<ResponseEntity<Task>> result = Optional.ofNullable(taskController.findByDate(taskDate));
            //then
            assertTrue(result.isPresent());
            assertEquals(result.get().getBody(),task);
            System.out.println("result is "+result.get().getBody());
            System.out.println("tested task object :"+task );
        }
        @Test
        void itShouldCheckIfTaskisNotFoundByDate() throws ResponseStatusException{
            //given
            LocalDate taskDate = LocalDate.of(2023, 7, 27);
            when(taskService.findByDate(taskDate)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

            //when
            ResponseStatusException exception = assertThrows(ResponseStatusException.class,() -> {
                taskController.findByDate(taskDate);
            });
            //then
            assertEquals(HttpStatus.NOT_FOUND,exception.getStatus());
            System.out.println("exception status : "+exception.getStatus());

        }
        @Test
        void  itShouldCheckIfTaskIsFoundByDateTimeReminderBetween(){
            //given
            LocalDateTime startDate = LocalDateTime.of(2023, 6, 28, 9, 0);
            LocalDateTime endDate = LocalDateTime.of(2023, 7, 28, 9, 0);

            Task task = new Task() {{
                setId(1L);
                setName("Example Task");
                setContent("This is an example task");
                setTaskPriority(Priority.HIGH);
                setTaskstatus(TaskStatus.IN_PROGRESS);
                setTime(LocalTime.now());
                setDate(LocalDate.now());
                setCategory("Example Category");
                setTags(new HashSet<>(Arrays.asList("tag1", "tag2", "tag3")));
                setDateTimeReminder(LocalDateTime.now());
                setTaskList(new TodoList() {{ setName("Example TodoList"); }});
            }};
            //when
            when(taskService.findByDateTimeReminderBetween(startDate,endDate)).thenReturn(Optional.of(task));
            Optional<ResponseEntity<Task>> result = Optional.ofNullable(taskController.finByDateTimeReminderBetween(startDate,endDate));
            //then
            assertTrue(result.isPresent());
            assertEquals(result.get().getBody(),task);
            System.out.println("result is "+result.get().getBody());
            System.out.println("tested task object :"+task );

        }

        @Test
        void itShouldCheckIfTaskIsNotFoundByDateTimeReminderBetween() throws ResponseStatusException{
              //given
            LocalDateTime startDate = LocalDateTime.of(2023, 6, 28, 9, 0);
            LocalDateTime endDate = LocalDateTime.of(2023, 7, 28, 9, 0);
            when(taskService.findByDateTimeReminderBetween(startDate,endDate)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

            //when
            ResponseStatusException exception = assertThrows(ResponseStatusException.class,()-> {
                taskController.finByDateTimeReminderBetween(startDate,endDate);
            });
            //then
                  assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
                  System.out.println(" expection status : "+exception.getStatus());
                  System.out.println("Http status result : "+HttpStatus.NOT_FOUND);
                  System.out.println("excpetion message : "+exception.getMessage());
                    // assertEquals("task not found",exception.getReason());

        }
         @Test
         void shouldFindByTaskStatusSuccessfully() throws ElementNotFoundException {
        // Given
        TaskStatus status = TaskStatus.IN_PROGRESS;
        Task task = new Task();
        task.setId(1L);
        task.setTaskstatus(status);


        // When
             when(taskService.findByTaskstatus(status)).thenReturn(Optional.of(task));

                     Optional<ResponseEntity<Task>> result = Optional.ofNullable(taskController.findByTaskStatus(status));

        // Then
        assertEquals(HttpStatus.OK, result.get().getStatusCode());
        assertEquals(task, result.get().getBody());
    }

         @Test
        void shouldHandleNotFoundWhenFindByTaskStatus() throws ResponseStatusException {
        // Given
        TaskStatus status = TaskStatus.IN_PROGRESS;

        given(taskService.findByTaskstatus(status)).willThrow(new ElementNotFoundException("Task not found"));

        // When and Then
        assertThrows(ResponseStatusException.class, () -> {
            taskController.findByTaskStatus(status);
        });
    }

       @Test
        void itShouldTestIfTaskIsFoundByPriority() throws ResponseStatusException {
            // given
            Priority taskpriority = Priority.HIGH;
              Task task = new Task() {{
                setId(1L);
                setName("Example Task");
                setContent("This is an example task");
                setTaskPriority(Priority.HIGH);
                setTaskstatus(TaskStatus.IN_PROGRESS);
                setTime(LocalTime.now());
                setDate(LocalDate.now());
                setCategory("Example Category");
                setTags(new HashSet<>(Arrays.asList("tag1", "tag2", "tag3")));
                setDateTimeReminder(LocalDateTime.now());
                setTaskList(new TodoList() {{ setName("Example TodoList"); }});
            }};
        //when
            when(taskService.findByTaskPriority(taskpriority)).thenReturn(Optional.ofNullable(task));
            Optional<ResponseEntity<Task>> result =  Optional.ofNullable(taskController.findByTaskPriority(taskpriority));

            //then
           assertTrue(result.isPresent());
           assertEquals(result.get().getBody(),task);
           System.out.println("result is "+result.get().getBody());
           System.out.println("tested task object :"+task );

        }

        @Test
    void itShouldTestIfTaskIsNotFoundByPriority() throws  ResponseStatusException{
                 // given
            Priority taskpriority = Priority.HIGH;
            //when
            when(taskService.findByTaskPriority(taskpriority)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
            ResponseStatusException exception = assertThrows(ResponseStatusException.class,()->{
                taskController.findByTaskPriority(taskpriority);});
            //then
            assertEquals(HttpStatus.NOT_FOUND,exception.getStatus());
        }

        @Test
    void itShouldCheckIfTaskIsFoundByCategory() throws ResponseStatusException {

            //given
            String category = "category1";

                   Task task = new Task() {{
                setId(1L);
                setName("Example Task");
                setContent("This is an example task");
                setTaskPriority(Priority.HIGH);
                setTaskstatus(TaskStatus.IN_PROGRESS);
                setTime(LocalTime.now());
                setDate(LocalDate.now());
                setCategory("Example Category");
                setTags(new HashSet<>(Arrays.asList("tag1", "tag2", "tag3")));
                setDateTimeReminder(LocalDateTime.now());
                setTaskList(new TodoList() {{ setName("Example TodoList"); }});
            }};
            //when
            when(taskService.findByCategory(category)).thenReturn(Optional.ofNullable(task));
            Optional<ResponseEntity<Task>> result =  Optional.ofNullable(taskController.findbycategory(category));

            //then
            assertEquals(result.get().getBody(),task);



        }

    }
