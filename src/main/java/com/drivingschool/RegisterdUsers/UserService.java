package com.drivingschool.RegisterdUsers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivingschool.Blog.Blog;
import com.drivingschool.RegisterdUsers.Dto.UserRequestDTO;
import com.drivingschool.RegisterdUsers.Dto.UserResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToResponseDTO).collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToResponseDTO).orElse(null);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        DrivingUser user = convertToEntity(userRequestDTO);
        userRepository.save(user);
        return convertToResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        DrivingUser user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userRequestDTO.getName());
        user.setAddress(userRequestDTO.getAddress());
        user.setNumber(userRequestDTO.getPhoneNumber());
        userRepository.save(user);
        return convertToResponseDTO(user);
    }

    
    public DrivingUser findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private UserResponseDTO convertToResponseDTO(DrivingUser user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setAddress(user.getAddress());
        userResponseDTO.setPhoneNumber(user.getNumber());
        userResponseDTO.setBlogIds(user.getBlogs().stream().map(Blog::getId).collect(Collectors.toList()));
        return userResponseDTO;
    }

    private DrivingUser convertToEntity(UserRequestDTO userRequestDTO) {
        DrivingUser user = new DrivingUser();
        user.setName(userRequestDTO.getName());
        user.setAddress(userRequestDTO.getAddress());
        user.setNumber(userRequestDTO.getPhoneNumber());
        // Blogs are handled separately
        return user;
    }
}