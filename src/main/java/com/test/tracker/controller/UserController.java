package com.test.tracker.controller;

import com.test.tracker.dto.CreateUserDto;
import com.test.tracker.dto.UserDto;
import com.test.tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserDto> createUnit(@Valid @RequestBody CreateUserDto unitDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(unitDto));
  }
}
