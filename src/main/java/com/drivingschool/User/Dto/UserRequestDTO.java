package com.drivingschool.User.Dto;
import java.util.List;


public class UserRequestDTO {

    private String name;
    private String address;
    private String phoneNumber;
    private List<Long> blogIds;
    public String getName() {
        return name;
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
    public List<Long> getBlogIds() {
        return blogIds;
    }
    public void setBlogIds(List<Long> blogIds) {
        this.blogIds = blogIds;
    }

    
}
