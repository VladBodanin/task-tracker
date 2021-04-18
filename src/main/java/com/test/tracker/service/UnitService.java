package com.test.tracker.service;

import com.test.tracker.dto.CreateUnitDto;
import com.test.tracker.dto.UnitDto;
import com.test.tracker.entity.UnitEntity;
import com.test.tracker.exception.EntityAlreadyExists;
import com.test.tracker.exception.NotFoundException;
import com.test.tracker.mapper.UnitMapper;
import com.test.tracker.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@RequiredArgsConstructor
@Service
public class UnitService {

  private final UnitRepository unitRepository;
  private final UnitMapper unitMapper;

  @Transactional
  public UnitDto createUnit(CreateUnitDto unitDto) {
    unitRepository
        .findByName(unitDto.getName())
        .ifPresent(
            u -> {
              throw new EntityAlreadyExists("Unit with this name already exists");
            });

    UnitEntity unitEntity = unitRepository.save(unitMapper.toEntity(unitDto));

    return unitMapper.toDto(unitEntity);
  }

   UnitEntity getByIdOrThrow(BigInteger id) {
    return unitRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Cannot find unit with id:" + id));
  }
}
