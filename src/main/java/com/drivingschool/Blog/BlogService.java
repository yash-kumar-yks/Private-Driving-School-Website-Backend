package com.drivingschool.Blog;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivingschool.Blog.Dtos.BlogResponseDTO;
import com.drivingschool.RegisterdUsers.DrivingUser;
import com.drivingschool.RegisterdUsers.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public List<BlogResponseDTO> getAllBlogs() {
        return blogRepository.findAll().stream().map(this::convertToResponseDTO).collect(Collectors.toList());
    }

    public List<BlogResponseDTO> getBlogsByUserEmail(String email) {
        DrivingUser user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getBlogs().stream().map(this::convertToResponseDTO).collect(Collectors.toList());
    }

    private BlogResponseDTO convertToResponseDTO(Blog blog) {
        BlogResponseDTO blogResponseDTO = new BlogResponseDTO();
        blogResponseDTO.setId(blog.getId());
        blogResponseDTO.setTitle(blog.getTitle());
        blogResponseDTO.setContent(blog.getContent());
        blogResponseDTO.setUserId(blog.getUser().getId());
        return blogResponseDTO;
    }
}