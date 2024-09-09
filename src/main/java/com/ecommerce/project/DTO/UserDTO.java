package com.ecommerce.project.DTO;

import com.ecommerce.project.model.Role;

public record UserDTO(

    Long userId,
    String username,
    String fname,
    String lname,
    String email,
    Role role

) {
}
