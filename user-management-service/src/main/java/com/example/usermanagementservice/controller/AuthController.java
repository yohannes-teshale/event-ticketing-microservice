package com.example.usermanagementservice.controller;

import com.example.usermanagementservice.service.AuthService;
import com.example.usermanagementservice.service.dtos.JwtTokenDto;
import com.example.usermanagementservice.service.dtos.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        var loginResponse = service.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/refreshToken")
    public JwtTokenDto refreshToken(@RequestBody JwtTokenDto refreshTokenRequest){
        return service.refreshToken(refreshTokenRequest);
    }
}
