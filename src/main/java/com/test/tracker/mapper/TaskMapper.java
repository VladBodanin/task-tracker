package com.test.tracker.mapper;

import com.test.tracker.dto.CreateTaskDto;
import com.test.tracker.dto.DetailedTaskDto;
import com.test.tracker.dto.TaskDto;
import com.test.tracker.dto.UpdateTaskDto;
import com.test.tracker.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

  TaskEntity toEntity(TaskDto taskDto);

  TaskEntity toEntity(CreateTaskDto taskDto);

  TaskEntity toEntity(UpdateTaskDto taskDto);

  TaskDto toDto(TaskEntity taskEntity);

  DetailedTaskDto toDetailedTaskDto(TaskEntity taskEntity);
}
