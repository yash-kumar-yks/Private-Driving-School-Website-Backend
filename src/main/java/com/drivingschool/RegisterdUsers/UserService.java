package com.drivingschool.RegisterdUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.drivingschool.Blog.Blog;
import com.drivingschool.Blog.Dtos.BlogDTO;
import com.drivingschool.RegisterdUsers.Dto.UserRequestDTO;
import com.drivingschool.RegisterdUsers.Dto.UserResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToResponseDTO).collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToResponseDTO).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<BlogDTO> getBlogsByUserEmail(String email) {
        Optional<DrivingUser> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            DrivingUser user = optionalUser.get();
            return user.getBlogs().stream().map(this::convertToBlogDTO).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        DrivingUser user = convertToEntity(userRequestDTO);
        userRepository.save(user);
        return convertToResponseDTO(user);
    }

    public UserResponseDTO findUserByEmail(String email) {
        DrivingUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToResponseDTO(user);
    }

    private UserResponseDTO convertToResponseDTO(DrivingUser user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setAddress(user.getAddress());
        userResponseDTO.setPhoneNumber(user.getNumber());
        userResponseDTO.setEmail(user.getEmail());

        List<BlogDTO> blogDTOs = user.getBlogs().stream().map(blog -> {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setUserId(user.getId());
            blogDTO.setTitle(blog.getTitle());
            blogDTO.setContent(blog.getContent());
            return blogDTO;
        }).collect(Collectors.toList());

        userResponseDTO.setBlogs(blogDTOs);
        return userResponseDTO;
    }

    private DrivingUser convertToEntity(UserRequestDTO userRequestDTO) {
        DrivingUser user = new DrivingUser();
        user.setName(userRequestDTO.getName());
        user.setAddress(userRequestDTO.getAddress());
        user.setNumber(userRequestDTO.getPhoneNumber());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        List<Blog> blogs = userRequestDTO.getBlogs().stream().map(blogDTO -> {
            Blog blog = new Blog();
            blog.setTitle(blogDTO.getTitle());
            blog.setContent(blogDTO.getContent());
            blog.setUser(user);
            return blog;
        }).collect(Collectors.toList());

        user.setBlogs(blogs);
        return user;
    }

    private BlogDTO convertToBlogDTO(Blog blog) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setUserId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setContent(blog.getContent());
        return blogDTO;
    }

    public UserResponseDTO addBlogsToUser(String email, List<BlogDTO> blogDTOs) {
        Optional<DrivingUser> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            DrivingUser user = optionalUser.get();
            List<Blog> blogs = blogDTOs.stream().map(blogDTO -> {
                Blog blog = new Blog();
                blog.setTitle(blogDTO.getTitle());
                blog.setContent(blogDTO.getContent());
                blog.setUser(user);
                return blog;
            }).collect(Collectors.toList());

            user.getBlogs().addAll(blogs);
            userRepository.save(user);
            return convertToResponseDTO(user);
        } else {
            throw new UserNotFoundException("User with email " + email + " not found.");
        }
    }

    public UserResponseDTO authenticateUser(String email, String password) {
        DrivingUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Check if passwords match. Implement in frontend for login purpose
        if(passwordEncoder.matches(password, user.getPassword())){
            return convertToResponseDTO(user);
        }
        else
        return null;
    }

    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}