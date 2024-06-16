package com.drivingschool.RegisterdUsers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drivingschool.Blog.Dtos.BlogDTO;
import com.drivingschool.RegisterdUsers.Dto.UserRequestDTO;
import com.drivingschool.RegisterdUsers.Dto.UserResponseDTO;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

     @GetMapping("/{email}/blogs")
    public List<BlogDTO> getBlogsByUserEmail(@PathVariable String email) {
        return userService.getBlogsByUserEmail(email);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        return userResponseDTO != null ? ResponseEntity.ok(userResponseDTO) : ResponseEntity.notFound().build();
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @PostMapping("/{email}/blogs")
    public UserResponseDTO addBlogsToUser(@PathVariable String email, @RequestBody List<BlogDTO> blogDTOs) {
        return userService.addBlogsToUser(email, blogDTOs);
    }

}
