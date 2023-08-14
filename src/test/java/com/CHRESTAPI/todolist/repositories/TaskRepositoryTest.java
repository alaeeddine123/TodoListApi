package com.CHRESTAPI.todolist.repositories;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.entities.TodoList;
import com.CHRESTAPI.todolist.entities.User;
import com.CHRESTAPI.todolist.enums.Priority;
import com.CHRESTAPI.todolist.enums.Role;
import com.CHRESTAPI.todolist.enums.TaskStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")

public class TaskRepositoryTest {

    @Autowired
    private   TaskRepository taskRepository;

    @Autowired
    private  TodoListRepository todoListRepository;

    @Autowired
    private UserRepository userRepository;

     @AfterEach
    void tearDown(){
        taskRepository.deleteAll();
    }

    @Transactional
    @Test
    void itShouldCheckIfTaskIsFoundByTaskList() {
         //given

         User user = User.builder()
                .username("john_doe3")
                .password("password123")
                .email("john1@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .build();

         userRepository.save(user);

          TodoList todoList = TodoList.builder()
                  .name("todoList1")
                  .createdDate(LocalDate.of(2023, 7, 27))
                  .user(user).build();

           todoListRepository.save(todoList);



        Task task = Task.builder()
                .name("task1")
                .content("task 1 content")
                .taskPriority(Priority.HIGH)
                .taskstatus(TaskStatus.DONE)
                .time(LocalTime.of(12, 30))
                .date(LocalDate.of(2023, 7, 27))
                .category("category 1")
                .tags(Set.of("tag1", "tag2", "tag3"))
                .dateTimeReminder(LocalDateTime.of(2023, 7, 28, 9, 0))
                .taskList(todoList)
                .build();
        taskRepository.save(task);
           //when
        Optional<Task> result = taskRepository.findByTaskList(todoList);
        //then
        assertEquals(Optional.of(task),result);
        // Using SLF4J to log the expected and actual results
    Logger logger = LoggerFactory.getLogger(TaskRepositoryTest.class);
    logger.info("Expected Result: {}", Optional.of(task));
    logger.info("Actual Result: {}", result);
}

    @Test
    @Transactional
    void itShouldCheckIfTaskIsNotFoundBytaskList(){
        //given
        User user = User.builder()
                .username("john_doe3")
                .password("password123")
                .email("john3@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .build();
         userRepository.save(user);

          TodoList todoList = TodoList.builder()
                  .name("todoList1")
                  .createdDate(LocalDate.of(2023, 7, 27))
                  .user(user).build();

           todoListRepository.save(todoList);

        //when
        Optional<Task> result = taskRepository.findByTaskList(todoList);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    @Transactional
    void itShouldCheckIfTaskIsFoundByTaskStatus() {

    // given
    TaskStatus taskStatus = TaskStatus.IN_PROGRESS;

    TodoList todoList = new TodoList();
    todoListRepository.save(todoList);

    // Create and save a Task entity with the desired task status
    Task task = Task.builder()
            .name("task1")
            .content("task 1 content")
            .taskPriority(Priority.HIGH)
            .taskstatus(taskStatus) // Set the task status here
            .time(LocalTime.of(12, 30))
            .date(LocalDate.of(2023, 7, 27))
            .category("category 1")
            .tags(Set.of("tag1", "tag2", "tag3"))
            .dateTimeReminder(LocalDateTime.of(2023, 7, 28, 9, 0))
            .build();

           taskRepository.save(task);

    // when
    // Find the task by the task status
    Optional<Task> result = taskRepository.findByTaskstatus(taskStatus);

    // then
    // Assert that the result contains the task you saved
    assertEquals(Optional.of(task), result);

    }

    @Test
    void itShouldCheckIfTaskIsNotFoundByTaskStatus(){

          TaskStatus taskStatus = TaskStatus.IN_PROGRESS;

        // when
       // Find the task by the task status
        Optional<Task> result = taskRepository.findByTaskstatus(taskStatus);

        //then
        assertFalse(result.isPresent());



    }

    @Test
     @Transactional
        void itShouldCheckIfTaskIsFoundByDate() {

    // given
          LocalDate taskDate = LocalDate.of(2023, 7, 27);


    TodoList todoList = new TodoList();
    todoListRepository.save(todoList);

    // Create and save a Task entity with the desired task status
    Task task = Task.builder()
            .name("task1")
            .content("task 1 content")
            .taskPriority(Priority.HIGH)
            .time(LocalTime.of(12, 30))
            .date(LocalDate.of(2023, 7, 27))
            .category("category 1")
            .tags(Set.of("tag1", "tag2", "tag3"))
            .dateTimeReminder(LocalDateTime.of(2023, 7, 28, 9, 0))
            .build();

           taskRepository.save(task);

    // when
    // Find the task by the task status
    Optional<Task> result = taskRepository.findByDate(taskDate);

    // then
    // Assert that the result contains the task you saved
     assertEquals(Optional.of(task), result);
     }

        @Test
    void itShouldCheckIfTaskIsNotFoundByDate(){

         // given
          LocalDate taskDate = LocalDate.of(2023, 7, 27);

        // when
       // Find the task by the task date
        Optional<Task> result = taskRepository.findByDate(taskDate);

        //then
        assertFalse(result.isPresent());
    }

     @Test
     @Transactional
        void itShouldCheckIfTaskIsFoundByDateTimeReminderBetween() {

    // given
          LocalDateTime startDate = LocalDateTime.of(2023, 7, 28, 9, 0);
          LocalDateTime endDate = LocalDateTime.of(2023, 7, 30, 9, 0);


    TodoList todoList = new TodoList();
    todoListRepository.save(todoList);

    // Create and save a Task entity with the desired task status
    Task task = Task.builder()
            .name("task1")
            .content("task 1 content")
            .taskPriority(Priority.HIGH)
            .time(LocalTime.of(12, 30))
            .date(LocalDate.of(2023, 7, 27))
            .category("category 1")
            .tags(Set.of("tag1", "tag2", "tag3"))
            .dateTimeReminder(LocalDateTime.of(2023, 7, 28, 9, 0))
            .build();

           taskRepository.save(task);

    // when
    // Find the task by the task status
    Optional<Task> result = taskRepository.findByDateTimeReminderBetween(startDate,endDate);

    // then
    // Assert that the result contains the task you saved
     assertEquals(Optional.of(task), result);

    }

     @Test
     @Transactional
     void itShouldCheckIfTaskIsNotFoundByDateTimeReminderBetween(){

         // given
          LocalDateTime startDate = LocalDateTime.of(2023, 7, 28, 9, 0);
          LocalDateTime endDate = LocalDateTime.of(2023, 7, 30, 9, 0);


        // when
       // Find the task by the task date
        Optional<Task> result = taskRepository.findByDateTimeReminderBetween(startDate,endDate);

        //then
        assertFalse(result.isPresent());
    }

    @Test
     @Transactional
        void itShouldCheckIfTaskIsFoundByTaskPriority() {

    // given
         Priority taskpriority = Priority.LOW;


    // Create and save a Task entity with the desired task status
    Task task = Task.builder()
            .name("task1")
            .content("task 1 content")
            .taskPriority(Priority.LOW)
            .time(LocalTime.of(12, 30))
            .date(LocalDate.of(2023, 7, 27))
            .category("category 1")
            .tags(Set.of("tag1", "tag2", "tag3"))
            .dateTimeReminder(LocalDateTime.of(2023, 7, 28, 9, 0))
            .build();

           taskRepository.save(task);

    // when
    // Find the task by the task status
    Optional<Task> result = taskRepository.findByTaskPriority(taskpriority);

    // then
    // Assert that the result contains the task you saved
     assertEquals(Optional.of(task), result);

    }

     @Test
    void itShouldCheckIfTaskIsNotFoundByTaskPriority(){

         // given
          Priority taskpriority = Priority.LOW;

        // when
       // Find the task by the task date
        Optional<Task> result = taskRepository.findByTaskPriority(taskpriority);

        //then
        assertFalse(result.isPresent());
    }

    @Test
        void itShouldCheckIfTaskIsFoundByTaskCategory() {

         // given
         String taskCategory = "Category1";

         // Create and save a Task entity with the desired task status
         Task task = Task.builder()
                 .name("task1")
                 .content("task 1 content")
                 .taskPriority(Priority.LOW)
                 .time(LocalTime.of(12, 30))
                 .date(LocalDate.of(2023, 7, 27))
                 .category("category 1")
                 .tags(Set.of("tag1", "tag2", "tag3"))
                 .dateTimeReminder(LocalDateTime.of(2023, 7, 28, 9, 0))
                 .build();

         taskRepository.save(task);

         // when
         // Find the task by the task status
         Optional<Task> result = taskRepository.findByCategory(taskCategory);
     }

     @Test
    void itShouldCheckIfTaskIsNotFoundByTaskCategory(){

         // given
               String taskCategory = "Category1";

        // when
       // Find the task by the task date
        Optional<Task> result = taskRepository.findByCategory(taskCategory);

        //then
        assertFalse(result.isPresent());
    }

    @Test
        void itShouldCheckIfTaskIsFoundByTagsIn() {

         // given
         Set<String> taskTags = Set.of("tag1", "tag2", "tag3");

         // Create and save a Task entity with the desired task status
         Task task = Task.builder()
                 .name("task1")
                 .content("task 1 content")
                 .taskPriority(Priority.LOW)
                 .time(LocalTime.of(12, 30))
                 .date(LocalDate.of(2023, 7, 27))
                 .category("category 1")
                 .tags(Set.of("tag1", "tag2", "tag3"))
                 .dateTimeReminder(LocalDateTime.of(2023, 7, 28, 9, 0))
                 .build();

         taskRepository.save(task);

         // when
         // Find the task by the task status
         Optional<Task> result = taskRepository.findByTagsIn(taskTags);
     }

     @Test
    void iTShouldCheckIfTaskIsNotFoundByTagsIn(){

         // given
         Set<String> taskTags = Set.of("tag1", "tag2", "tag3");

         //when
          Optional<Task> result = taskRepository.findByTagsIn(taskTags);

          //then
           assertFalse(result.isPresent());
     }


    }


