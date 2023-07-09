package com.CHRESTAPI.todolist.repositories;

import com.CHRESTAPI.todolist.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Qualifier("users")
public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username OR u.email = :email")
    Optional<User> findByUsernameOrEmail(@Param("email") String email, @Param("username") String username);

     List<User> findByUsernameContains(String keyword);
    Optional<User> findByUsername(String username);



}
