package com.example.usermanagementservice.service.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenDto {
    private String accessToken;
    private String refreshToken;
}
