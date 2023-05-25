package com.example.usermanagementservice.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private JwtTokenDto jwtResponse;
    //private UserResponseDto userResponse;

    public LoginResponseDto(JwtTokenDto jwtResponse){
        this.jwtResponse = jwtResponse;
    }
}
