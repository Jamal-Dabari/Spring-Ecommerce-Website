package com.ecommerce.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;

@Entity(name = "Users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  @Column(unique = true)
  private String username;
  private String fname;
  private String lname;
  private String password;
  @Column(unique = true)
  private String email;
  @Enumerated(EnumType.STRING)
  private Role role;
  private Boolean locked;
  private Boolean enabled;

  public User(String fname, String lname, Long userId, String password, String email, String username, Role role,
      Boolean locked, Boolean enabled) {
    this.fname = fname;
    this.lname = lname;
    this.userId = userId;
    this.password = password;
    this.email = email;
    this.username = username;
    this.role = role;
    this.locked = locked;
    this.enabled = enabled;
  }

  public User() {
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

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
    return Collections.singletonList(authority);
  }

  @Override
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

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  public boolean isEnabled() {
    return enabled;
  }

}
