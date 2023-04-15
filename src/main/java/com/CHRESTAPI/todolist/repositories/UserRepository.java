package com.CHRESTAPI.todolist.repositories;

import com.CHRESTAPI.todolist.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Qualifier("users")
public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByEmail(String email);
    User findByUsernameOrEmail(String email , String username);
    List<User> findByUsernameContains(String keyword);
    Optional<User> findByUsername(String username);
    List<User> findAll();


}
