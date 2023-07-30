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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
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


    @Test
      @Transactional
    void itShouldCheckIfTaskIsFoundByTaskList() {
         //given

         User user = User.builder()
                .username("john_doe3")
                .password("password123")
                .email("john2@example.com")
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
    @Transactional
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
    @Transactional
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





  /*
    List<Task> findByTagsIn(Set<String> tags);
    Task save(Task task);
   */



    }


