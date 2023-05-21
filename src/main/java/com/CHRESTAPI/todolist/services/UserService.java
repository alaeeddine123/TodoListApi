package com.CHRESTAPI.todolist.services;

import com.CHRESTAPI.todolist.dto.UserDto;
import com.CHRESTAPI.todolist.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public interface UserService {

    Optional<User> finById(Long id);

    Optional<User> findByUsername(String username);

    public List<UserDto> findAll();

    void createUser(User user);



    UserDto save(UserDto userDto);

    UserDto updateUser(UserDto userDto);
}
