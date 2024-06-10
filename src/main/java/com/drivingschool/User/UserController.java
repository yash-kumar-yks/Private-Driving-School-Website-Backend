package com.drivingschool.User;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@RequestMapping(path = "/Users")

public class UserController {
    private final UserService userservice;

    @Autowired
    public UserController(UserService userService){
        this.userservice=userService;
    }
    @GetMapping
    public User GetUser(@RequestParam String mail) {
        return userservice.GetUser(mail);
    }
    

}
