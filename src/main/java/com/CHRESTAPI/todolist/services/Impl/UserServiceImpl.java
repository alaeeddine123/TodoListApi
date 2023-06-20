package com.CHRESTAPI.todolist.services.Impl;

import com.CHRESTAPI.todolist.dto.UserDto;
import com.CHRESTAPI.todolist.entities.User;
import com.CHRESTAPI.todolist.repositories.UserRepository;
import com.CHRESTAPI.todolist.services.UserService;
import com.CHRESTAPI.todolist.validators.UserValidator;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository ;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public Optional<User> finById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository
                .findAll()
                .stream().map(UserDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void createUser(User user) {
        UserValidator.validate(user);
        userRepository.save(user);
    }




    @Override
    public UserDto save(UserDto userDto) {
        User user = userDto.toEntity(userDto); // Convert UserDto to User entity

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = UserDto.fromEntity(savedUser); // Convert User entity to UserDto


        return savedUserDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userDto.getId()));

        existingUser.setUsername(userDto.getUsername());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());

        User updatedUser = userRepository.save(existingUser);
        return UserDto.fromEntity(updatedUser);
    }


}
