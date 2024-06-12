package com.drivingschool.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivingschool.Blog.Blog;
import com.drivingschool.User.Dto.UserRequestDTO;
import com.drivingschool.User.Dto.UserResponseDTO;

import java.util.List;
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
        User user = convertToEntity(userRequestDTO);
        userRepository.save(user);
        return convertToResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userRequestDTO.getName());
        user.setAddress(userRequestDTO.getAddress());
        user.setNumber(userRequestDTO.getPhoneNumber());
        userRepository.save(user);
        return convertToResponseDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponseDTO convertToResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setAddress(user.getAddress());
        userResponseDTO.setPhoneNumber(user.getNumber());
        userResponseDTO.setBlogIds(user.getBlogs().stream().map(Blog::getId).collect(Collectors.toList()));
        return userResponseDTO;
    }

    private User convertToEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setAddress(userRequestDTO.getAddress());
        user.setNumber(userRequestDTO.getPhoneNumber());
        // Blogs are handled separately
        return user;
    }
}
