package com.ecommerce.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.service.UserServiceImp;
import com.ecommerce.project.exceptions.EmailAlreadyTakenException;
import com.ecommerce.project.exceptions.UsernameNotFoundException;
import com.ecommerce.project.model.RegistrationBox;
import com.ecommerce.project.model.User;

@RestController
@RequestMapping("/auth")
public class UserController {

  @Autowired
  private UserServiceImp userService;

  @GetMapping("/admin/users")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @ExceptionHandler({ EmailAlreadyTakenException.class })
  public ResponseEntity<String> handleEmailException() {
    return new ResponseEntity<String>("Email Provided is already in use", HttpStatus.CONFLICT);

  }

  @GetMapping("/public/register")
  public String showRegistrationForm() {
    return "Registration";
  }

  @PostMapping("/public/register")
  public ResponseEntity<String> createUser(@RequestBody RegistrationBox registrationBox) {

    userService.createUser(registrationBox);
    return new ResponseEntity<>("User has been created", HttpStatus.CREATED);
  }

  @PostMapping("/public/login")
  public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
    try {
      userService.loginUser(username, password);
      return ResponseEntity.ok("Login successful");
    } catch (UsernameNotFoundException e) {
      return new ResponseEntity<>("Login Failed: User not found", HttpStatus.UNAUTHORIZED);
    }
  }

  @GetMapping("/public/login")
  public String loginPage() {
    return "Login";
  }

  @GetMapping("/success")
  public String success() {
    return "success";
  }

}
