package com.springboot.users_example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.users_example.model.transferObject.UsersDTO;
import com.springboot.users_example.services.interfaces.UsersInterface;

@RestController
public class UserController {

    @Autowired
    private UsersInterface usersInterface;

    @GetMapping("/users")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        try {
            List<UsersDTO> users = usersInterface.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}