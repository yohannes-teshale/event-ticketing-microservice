package com.example.usermanagementservice.service.dtos;


import com.example.usermanagementservice.model.User;

public class UserDTOAdapter {
    public static User getUser(UserRequest userRequest){
        User user= new User();
        user.setGender(userRequest.getGender());
        user.setFirstName(userRequest.getFirstName());
        user.setLastname(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        return user;
    }

}
