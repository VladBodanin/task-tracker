package com.test.tracker.mapper;

import com.test.tracker.dto.CreateUnitDto;
import com.test.tracker.dto.UnitDto;
import com.test.tracker.entity.UnitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UnitMapper {

  UnitEntity toEntity(CreateUnitDto unitDto);

  UnitDto toDto(UnitEntity unitEntity);
}
