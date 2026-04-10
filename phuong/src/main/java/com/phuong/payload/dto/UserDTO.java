package com.phuong.payload.dto;

import com.phuong.domain.AuthProvider;
import com.phuong.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private UserRole role;
    private String pasword;
    private LocalDateTime lastLogin;
}
