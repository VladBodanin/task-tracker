package com.test.tracker.service;

import com.test.tracker.dto.CreateTaskDto;
import com.test.tracker.dto.DetailedTaskDto;
import com.test.tracker.dto.TaskDto;
import com.test.tracker.dto.TaskState;
import com.test.tracker.dto.UpdateTaskDto;
import com.test.tracker.entity.TaskEntity;
import com.test.tracker.entity.UnitEntity;
import com.test.tracker.entity.UserEntity;
import com.test.tracker.exception.NotFoundException;
import com.test.tracker.mapper.TaskMapper;
import com.test.tracker.repository.TaskRepository;
import com.test.tracker.repository.spec.TaskByUnitNameSpecification;
import com.test.tracker.repository.spec.ResultTaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private final UserService userService;
  private final UnitService unitService;
  private final TaskMapper taskMapper;
  private final UserRateService userRateService;

  public Page<TaskDto> getTasks(Optional<String> unit, String sortOrder, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), "createdAt");
    Set<Specification<TaskEntity>> specifications = new HashSet<>();
    unit.ifPresent(e -> specifications.add(new TaskByUnitNameSpecification(e)));
    Page<TaskEntity> entityPage =
        taskRepository.findAll(new ResultTaskSpecification(specifications), pageable);
    List<TaskEntity> tasks = entityPage.toList();
    Map<BigInteger, BigDecimal> userRates = Collections.emptyMap();
    if (!tasks.isEmpty()) {
      List<BigInteger> userIds =
          tasks.stream()
              .flatMap(e -> Stream.of(e.getAuthor().getId(), e.getPerformer().getId()))
              .distinct()
              .collect(Collectors.toList());
      userRates = userRateService.getUserRates(userIds);
    }

    return new PageImpl<>(toTaskDtos(tasks, userRates), pageable, entityPage.getTotalElements());
  }

  public DetailedTaskDto getTask(BigInteger id) {
    return taskMapper.toDetailedTaskDto(getByIdOrThrow(id));
  }

  @Transactional
  public TaskDto createTask(CreateTaskDto taskDto) {
    UserEntity author = userService.getByIdOrThrow(taskDto.getAuthorId());
    UserEntity performer = userService.getByIdOrThrow(taskDto.getPerformerId());
    UnitEntity unit = unitService.getByIdOrThrow(taskDto.getUnitId());
    TaskEntity taskEntity = taskMapper.toEntity(taskDto);
    taskEntity.setState(TaskState.CREATED);
    taskEntity.setAuthor(author);
    taskEntity.setPerformer(performer);
    taskEntity.setUnit(unit);
    taskEntity = taskRepository.save(taskEntity);

    return taskMapper.toDto(taskEntity);
  }

  @Transactional
  public TaskDto updateTask(BigInteger id, UpdateTaskDto taskDto) {
    TaskEntity taskEntity = getByIdOrThrow(id);
    UserEntity author = userService.getByIdOrThrow(taskDto.getAuthorId());
    UserEntity performer = userService.getByIdOrThrow(taskDto.getPerformerId());
    UnitEntity unit = unitService.getByIdOrThrow(taskDto.getUnitId());
    taskEntity.setAuthor(author);
    taskEntity.setPerformer(performer);
    taskEntity.setUnit(unit);
    taskEntity.setCreatedAt(taskDto.getCreatedAt());
    taskEntity.setState(taskDto.getState());
    taskEntity.setDescription(taskDto.getDescription());
    taskEntity.setTopic(taskDto.getTopic());

    return taskMapper.toDto(taskEntity);
  }

  TaskEntity getByIdOrThrow(BigInteger id) {
    return taskRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Cannot find task with id:" + id));
  }

  private List<TaskDto> toTaskDtos(List<TaskEntity> tasks, Map<BigInteger, BigDecimal> userRates) {
    return tasks.stream()
        .map(taskMapper::toDto)
        .peek(
            e -> {
              e.getAuthor().setRate(userRates.getOrDefault(e.getAuthor().getId(), BigDecimal.ZERO));
              e.getPerformer()
                  .setRate(userRates.getOrDefault(e.getPerformer().getId(), BigDecimal.ZERO));
            })
        .collect(Collectors.toList());
  }
}
