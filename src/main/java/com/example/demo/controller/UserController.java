package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userRepository.findById(id).get();
    }

    /**
     * http://127.0.0.1:8080/user
     * {
     * "name":"duan",
     * "age":11
     * }
     *
     * @param user
     * @return
     */
    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
    }

    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "id") String property,
                               @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                               @RequestParam(defaultValue = "0") Integer page,
                               @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size, direction, property);
        return userRepository.findAll(pageable);
    }
}
