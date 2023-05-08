package com.CHRESTAPI.todolist.repositories;

import com.CHRESTAPI.todolist.entities.Task;
import com.CHRESTAPI.todolist.enums.priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByTaskList(String taskList);

    Optional<Task> findByTaskstatus(String status);

    List<Task> findByDate(LocalDate date);

    List<Task> findByDateTimeReminderBetween(LocalDateTime start, LocalDateTime end);

    List<Task> findByTaskPriority(priority priority);

    List<Task> findByCategory(String category);

    List<Task> findByTagsIn(Set<String> tags);
}
