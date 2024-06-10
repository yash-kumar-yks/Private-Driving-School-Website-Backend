package com.drivingschool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.drivingschool.User.User;

import org.springframework.web.bind.annotation.GetMapping;



@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@GetMapping("/")
	public User Hello(){
		return new User();
	}
	
	

}