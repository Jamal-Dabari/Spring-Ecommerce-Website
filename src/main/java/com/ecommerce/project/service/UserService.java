package com.ecommerce.project.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.project.DTO.UserDTO;
import com.ecommerce.project.model.RegistrationBox;
import com.ecommerce.project.model.User;

public interface UserService {

  List<UserDTO> getAllUsers();

  User createUser(RegistrationBox registrationBox);

  String deleteUser(Long userId);

  User loginUser(String username, String password);

  UserDetails loadUserByUsername(String username);

}
