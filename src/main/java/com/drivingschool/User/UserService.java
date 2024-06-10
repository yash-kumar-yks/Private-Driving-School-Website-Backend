package com.drivingschool.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private UserRepository userrepository;
    
     @Autowired
    public UserService(UserRepository userrepository){
        this.userrepository=userrepository;
    }



    public User GetUser(String mail){
        return new User();
    }
}
