package com.ecommerce.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.service.UserServiceImp;
import com.ecommerce.project.exceptions.EmailAlreadyTakenException;
import com.ecommerce.project.exceptions.UsernameNotFoundException;
import com.ecommerce.project.model.RegistrationBox;
import com.ecommerce.project.model.User;

@RestController
@Controller
@RequestMapping("/auth")
public class UserController {

  @Autowired
  private UserServiceImp userService;

  @GetMapping("/admin/users")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @ExceptionHandler({ EmailAlreadyTakenException.class })
  public ResponseEntity<String> handleEmailException() {
    return new ResponseEntity<String>("Email Provided is already in use", HttpStatus.CONFLICT);

  }

  @GetMapping("/public/register")
  public String showRegistrationForm() {
    return "Registration"; // Returns the registration.html page
  }

  // @Autowired
  @PostMapping("/public/register")
  public String createUser(@ModelAttribute RegistrationBox registrationBox) {
    userService.createUser(registrationBox);
    return "redirect:/";
  }

  @GetMapping("/login")
  public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
    try {
      User user = userService.loginUser(username, password);
      return new ResponseEntity<>("User Logged in", HttpStatus.OK);
    } catch (UsernameNotFoundException e) {
      return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }
  }

  @GetMapping("/success")
  public String success() {
    return "success";
  }

}
