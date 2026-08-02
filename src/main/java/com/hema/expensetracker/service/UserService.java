package com.hema.expensetracker.service;

import com.hema.expensetracker.entity.User;
import java.util.List;

public interface UserService {

    User addUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);

    User updateUser(int id, User user);

    void deleteUser(int id);
}