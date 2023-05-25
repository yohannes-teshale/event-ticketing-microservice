package com.example.usermanagementservice.service;


import com.example.usermanagementservice.config.CustomUserDetails;
import com.example.usermanagementservice.model.User;
import com.example.usermanagementservice.repository.UserRepository;
import com.example.usermanagementservice.service.dtos.UserDTOAdapter;
import com.example.usermanagementservice.service.dtos.UserRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> credential = userRepository.findByEmail(email);
        return credential.map(CustomUserDetails::new).orElse(null);
    }

    public List<User> getAllUsers(){
        List<User> allusers = userRepository.findAll();
        log.info("get a list of all users");
        return allusers;
    }

    public User addNewUser(UserRequest userRequest){
        User user = UserDTOAdapter.getUser(userRequest);
        userRepository.save(user);
        log.info("user saved successfully");
        return user;

    }
    public User getAUser(long userId){
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isPresent()) {
            User user = byId.get();
            if (user.isDeleted() == false) {
                log.info("returning user with id:{}", userId);
                return user;
            }
        }
            log.error("user with id:{} does not exits",userId);
            return null;


    }

    public User deleteUser(long userId){
        User user = getAUser(userId);
        if (user!=null){
            user.setDeleted(true);
            userRepository.save(user);
            log.info("user with id:{} deleted successfully",userId);
            return user;
        }
        else {
            log.error("can not delete user with id:{}",userId);
            return null;
        }

    }
}
