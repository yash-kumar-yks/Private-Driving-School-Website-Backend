package com.drivingschool.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.drivingschool.Blog.Dtos.BlogResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<BlogResponseDTO> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<BlogResponseDTO>> getBlogsByUserEmail(@PathVariable String email) {
        List<BlogResponseDTO> blogs = blogService.getBlogsByUserEmail(email);
        return blogs != null ? ResponseEntity.ok(blogs) : ResponseEntity.notFound().build();
    }
}

