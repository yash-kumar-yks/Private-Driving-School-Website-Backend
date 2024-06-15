package com.drivingschool.Blog;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivingschool.Blog.Dtos.BlogDTO;


@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

   public List<BlogDTO> getAllBlogs() {
        return blogRepository.findAll().stream().map(this::convertToBlogDTO).collect(Collectors.toList());
    }

     private BlogDTO convertToBlogDTO(Blog blog) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setUserId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setContent(blog.getContent());
        return blogDTO;
    }

}