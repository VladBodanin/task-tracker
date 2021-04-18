package com.test.tracker.controller;

import com.test.tracker.constraint.ValueOfEnum;
import com.test.tracker.dto.CommentDto;
import com.test.tracker.dto.CreateCommentDto;
import com.test.tracker.dto.CreateTaskDto;
import com.test.tracker.dto.DetailedTaskDto;
import com.test.tracker.dto.TaskDto;
import com.test.tracker.dto.UpdateTaskDto;
import com.test.tracker.service.CommentService;
import com.test.tracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {

  private final TaskService taskService;
  private final CommentService commentService;

  @GetMapping
  public ResponseEntity<Page<TaskDto>> getTasks(
      @RequestParam(required = false) Optional<String> unit,
      @ValueOfEnum(enumClass = Sort.Direction.class) @RequestParam String sortOrder,
      @Min(value = 0, message = "Page cannot be negative") @RequestParam int page,
      @Positive(message = "Size should be positive") @RequestParam int size) {
    return ResponseEntity.ok(taskService.getTasks(unit, sortOrder, page, size));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetailedTaskDto> getTask(@PathVariable BigInteger id) {
    return ResponseEntity.ok(taskService.getTask(id));
  }

  @PostMapping
  public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskDto task) {
    return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskDto> updateTask(
      @PathVariable BigInteger id, @Valid @RequestBody UpdateTaskDto task) {
    return ResponseEntity.ok(taskService.updateTask(id, task));
  }

  @PostMapping("/{taskId}/comments")
  public ResponseEntity<CommentDto> addComment(
      @PathVariable BigInteger taskId, @Valid @RequestBody CreateCommentDto commentDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(commentService.createComment(taskId, commentDto));
  }

  @DeleteMapping("/{taskId}/comments/{commentId}")
  public ResponseEntity<Void> deleteComment(
      @PathVariable BigInteger taskId, @PathVariable BigInteger commentId) {
    commentService.deleteComment(taskId, commentId);

    return ResponseEntity.noContent().build();
  }
}
