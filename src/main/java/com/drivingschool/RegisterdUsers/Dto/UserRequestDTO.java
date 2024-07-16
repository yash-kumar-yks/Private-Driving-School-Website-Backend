package com.drivingschool.RegisterdUsers.Dto;
import java.util.List;

import com.drivingschool.Blog.Dtos.BlogDTO;


public class UserRequestDTO {

    private String name;
    private String address;
  
    private String phoneNumber;
    private String email;
  
    private String password;
    private List<BlogDTO> blogs;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public List<BlogDTO> getBlogs() {
        return blogs;
    }
    public void setBlogs(List<BlogDTO> blogs) {
        this.blogs = blogs;
    }

    
}
