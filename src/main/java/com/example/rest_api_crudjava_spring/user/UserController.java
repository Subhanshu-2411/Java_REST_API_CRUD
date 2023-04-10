package com.example.rest_api_crudjava_spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User _user = userRepository.findById(id).get();
        _user.setName(user.getName());
        _user.setEmail(user.getEmail());
        return userRepository.save(_user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            userRepository.findById(id).get();
            userRepository.deleteById(id);
            return "User Deleted Succesfully";
        }
        catch(Exception e) {
            return "User Not Found";
        }
    }
}
