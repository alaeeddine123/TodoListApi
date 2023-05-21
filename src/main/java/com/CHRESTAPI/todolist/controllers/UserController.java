package com.CHRESTAPI.todolist.controllers;

import com.CHRESTAPI.todolist.dto.UserDto;

import com.CHRESTAPI.todolist.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@Slf4j
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PreAuthorize("hasRole('USER')")
    @GetMapping("/home")
    public String home() {
        return ("<h1>Welcome to home page <h1>");
    }

    // edit this endpoint so it wont return all user deatils
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getusers")
    public List <UserDto> getUsers() {
        return userService.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getuserbyid/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id){

       return  userService.finById(id).map(user -> ResponseEntity.status(HttpStatus.OK)
               .body(UserDto.fromEntity(user)))
               .orElse(ResponseEntity.notFound().build());

    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/createuser")
    public ResponseEntity<UserDto> createuser(@RequestBody UserDto userDto){
        UserDto createdUser = userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    public ResponseEntity<UserDto> updateuser(@RequestBody UserDto userDto){
        UserDto updatedUser = userService.updateUser(userDto);
        return null;
    }

}

