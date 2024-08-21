package com.ecommerce.project.service;

import java.util.List;

import com.ecommerce.project.model.RegistrationBox;
import com.ecommerce.project.model.User;

public interface UserService {

  List<User> getAllUsers();

  User createUser(RegistrationBox registrationBox);

  String deleteUser(Long userId);

  User loginUser(String username, String password);

}
