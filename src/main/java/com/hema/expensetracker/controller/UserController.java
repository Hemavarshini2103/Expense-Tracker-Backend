package com.hema.expensetracker.controller;

import com.hema.expensetracker.entity.User;
import com.hema.expensetracker.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Constructor Injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Add User
    @PostMapping("/add")
    public User addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    // Get All Users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User By Id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // Update User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id,
                           @Valid @RequestBody User updatedUser) {

        return userService.updateUser(id, updatedUser);
    }

    // Delete User
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {

        userService.deleteUser(id);

        return "User deleted successfully";
    }
}