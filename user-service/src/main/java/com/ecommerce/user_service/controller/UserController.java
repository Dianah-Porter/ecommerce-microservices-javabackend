package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User savedUser = userService.createUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());

    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User user) {


        return ResponseEntity.ok(
                userService.updateUser(id, user)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {


        userService.deleteUser(id);

        return ResponseEntity.ok(
                "User deleted successfully"
        );
    }

}