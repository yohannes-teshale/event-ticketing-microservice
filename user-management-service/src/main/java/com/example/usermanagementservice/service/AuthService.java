package com.example.usermanagementservice.service;

import com.example.usermanagementservice.model.User;
import com.example.usermanagementservice.repository.UserRepository;
import com.example.usermanagementservice.service.dtos.JwtTokenDto;
import com.example.usermanagementservice.service.dtos.LoginRequestDto;
import com.example.usermanagementservice.service.dtos.LoginResponseDto;
import com.example.usermanagementservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public LoginResponseDto login(LoginRequestDto loginRequest) {
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        Optional<User> userCredentialOptional = userRepository.findByEmail(loginRequest.getEmail());

        User userCredential = null;
        if(userCredentialOptional.isPresent()) {
            userCredential = userCredentialOptional.get();

            final String accessToken = jwtUtil.generateToken(userCredential);
            final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
            var jwtResponse = new JwtTokenDto(accessToken, refreshToken);

            return new LoginResponseDto(jwtResponse);//, UserResponseAdapter.getUserDtoFromUser(userCredential));
        }
        return null;
    }

    public JwtTokenDto refreshToken(JwtTokenDto refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
            return new JwtTokenDto(accessToken, refreshTokenRequest.getRefreshToken());
        }
        return new JwtTokenDto();
    }
}
