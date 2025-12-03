package com.fatec.web.controller;

import com.fatec.entity.User;
import com.fatec.repository.UserRepository;
import com.fatec.web.dto.CreateDto;
import com.fatec.web.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody @Valid CreateDto createDto) {
        User savedUser = repository.save(UserMapper.toEntity(createDto));
        return ResponseEntity.ok(savedUser);
    }

}
