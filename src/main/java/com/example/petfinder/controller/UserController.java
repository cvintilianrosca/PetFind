package com.example.petfinder.controller;

import com.example.petfinder.dto.IdResponseDto;
import com.example.petfinder.dto.MessageResponseDto;
import com.example.petfinder.dto.UserResponseDto;
import com.example.petfinder.dto.param.PostParameters;
import com.example.petfinder.entity.user.User;
import com.example.petfinder.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public String savePost(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.saveUser(user);
    }
    @GetMapping
    public User getUser(PostParameters postParameters) throws ExecutionException, InterruptedException {
        return userService.getUser(postParameters.getId());
    }

    @PutMapping
    public IdResponseDto updateUser(@RequestBody User user)  {
        return userService.updateUser(user);
    }

    @DeleteMapping
    public MessageResponseDto deleteUser(PostParameters postParameters){
        return userService.deleteUser(postParameters.getId());
    }
    @GetMapping(path = "/all")
    public UserResponseDto getAllPosts() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }
}
