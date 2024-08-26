package com.ecommerce.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.project.service.UserServiceImp;
import com.ecommerce.project.exceptions.EmailAlreadyTakenException;
import com.ecommerce.project.exceptions.UsernameNotFoundException;
import com.ecommerce.project.model.RegistrationBox;
import com.ecommerce.project.model.User;

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

  @PostMapping("/public/login")
  public String loginUser(@RequestParam String username, @RequestParam String password) {
    try {
      UserDetails user = userService.loginUser(username, password); // Change to UserDetails
      return "redirect:/";
    } catch (UsernameNotFoundException e) {
      return "fail";
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
