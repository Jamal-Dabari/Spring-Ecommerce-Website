package com.ecommerce.project.model;

import com.ecommerce.project.model.Role;

public class RegistrationBox {
  private String username;
  private String fname;
  private String lname;
  private String password;
  private String email;
  private Role role;

  public RegistrationBox(String fname, String lname, String password, String email, String username, Role role) {
    this.fname = fname;
    this.lname = lname;
    this.password = password;
    this.email = email;
    this.username = username;
    this.role = role;
  }

  public RegistrationBox() {
    super();
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

}
