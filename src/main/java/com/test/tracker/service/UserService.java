package com.test.tracker.service;

import com.test.tracker.dto.CreateUserDto;
import com.test.tracker.dto.UserDto;
import com.test.tracker.entity.UserEntity;
import com.test.tracker.exception.EntityAlreadyExists;
import com.test.tracker.exception.NotFoundException;
import com.test.tracker.mapper.UserMapper;
import com.test.tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final UnitService unitService;

  @Transactional
  public UserDto createUser(CreateUserDto userDto) {
    userRepository
        .findByName(userDto.getName())
        .ifPresent(
            u -> {
              throw new EntityAlreadyExists("User with this name already exists");
            });

    UserEntity userEntity = userMapper.toEntity(userDto);
    userEntity.setUnit(unitService.getByIdOrThrow(userDto.getUnitId()));
    userEntity = userRepository.save(userEntity);

    return userMapper.toDto(userEntity);
  }

  UserEntity getByIdOrThrow(BigInteger id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Cannot find user with id:" + id));
  }
}
