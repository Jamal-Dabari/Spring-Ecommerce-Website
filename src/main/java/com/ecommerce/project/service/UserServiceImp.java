package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.EmailAlreadyTakenException;
import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.RegistrationBox;
import com.ecommerce.project.model.Role;
import com.ecommerce.project.model.User;
import com.ecommerce.project.repositories.UserRepository;
import com.ecommerce.project.exceptions.EmailNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImp implements UserDetailsService {
  private Long newId = 1L;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private CartService cartService;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User createUser(RegistrationBox registrationBox) {

    if (userRepository.findByEmail(registrationBox.getEmail()).isPresent()) {
      throw new EmailAlreadyTakenException();
    }

    User user = new User();
    user.setUserId(newId++);
    user.setUsername(registrationBox.getUsername());
    user.setFname(registrationBox.getFname());
    user.setLname(registrationBox.getLname());
    user.setEmail(registrationBox.getEmail());
    user.setRole(Role.User);

    String encodedPassword = passwordEncoder.encode(registrationBox.getPassword());
    user.setPassword(encodedPassword);

    User newUser = userRepository.save(user);

    Cart cart = new Cart();
    cart.setUser(newUser);
    cartService.createCart(cart);

    return newUser;
  }

  public UserDetails loginUser(String username, String password) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("hey i couldnt find the username"));

    if (user != null && passwordEncoder.matches(password, user.getPassword())) {
      return user;
    } else {
      throw new UsernameNotFoundException("Invalid username or password");
    }

  }

  public String deleteUser(Long userId) {
    List<User> users = userRepository.findAll();
    User user = users.stream().filter(u -> u.getUserId().equals(userId)).findFirst()
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

    if (user == null) {
      return "not Found";
    }

    userRepository.delete(user);

    return "User with id " + userId + " has been deleted";

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("i couldnt find this username"));
  }

  public Optional<User> loadUserByEmail(String email) throws EmailNotFoundException {
    return Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException()));
  }

}
