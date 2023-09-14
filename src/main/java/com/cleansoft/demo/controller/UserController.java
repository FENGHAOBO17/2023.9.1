package com.cleansoft.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleansoft.demo.entity.ResponseEntity;
import com.cleansoft.demo.entity.User;
import com.cleansoft.demo.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired(required = false)
    UserService userService;

    @RequestMapping("/addUsers")
    public ResponseEntity addUser(@RequestBody User user) {
        return userService.addUsers(user);
    }

    @RequestMapping("/loginUsers")
    public ResponseEntity loginUsers(@RequestBody User user) {
        ResponseEntity responseEntity = userService.loginUsers(user);
        return responseEntity;
    }
    
    @RequestMapping("/testToken")
    public ResponseEntity testToken(@RequestBody User user) {
        ResponseEntity responseEntity = userService.testToken(user);
        return responseEntity;
    }
}