package com.drivingschool.Login;

import com.drivingschool.RegisterdUsers.UserService;
import com.drivingschool.RegisterdUsers.Dto.UserResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private  UserService userService;

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        return userService.authenticateUser(email, password);       
    }

    
}

