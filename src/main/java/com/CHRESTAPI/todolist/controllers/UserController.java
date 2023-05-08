package com.CHRESTAPI.todolist.controllers;

import com.CHRESTAPI.todolist.services.UserService;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


}
