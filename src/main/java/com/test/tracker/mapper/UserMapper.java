package com.test.tracker.mapper;

import com.test.tracker.dto.CreateUserDto;
import com.test.tracker.dto.UserDto;
import com.test.tracker.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserEntity toEntity(CreateUserDto unitDto);

    UserDto toDto(UserEntity unitEntity);
}
