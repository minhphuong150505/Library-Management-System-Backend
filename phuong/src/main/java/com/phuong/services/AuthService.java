package com.phuong.services;

import com.phuong.payload.dto.UserDTO;
import com.phuong.payload.response.AuthResponse;
import jakarta.security.auth.message.AuthException;

public interface AuthService {
    AuthResponse login(String email, String password) throws AuthException;

    AuthResponse signUp(UserDTO userDTO) throws AuthException;
}
