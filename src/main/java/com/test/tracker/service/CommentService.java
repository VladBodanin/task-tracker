package com.test.tracker.service;

import com.test.tracker.dto.CommentDto;
import com.test.tracker.dto.CreateCommentDto;
import com.test.tracker.entity.CommentEntity;
import com.test.tracker.entity.TaskEntity;
import com.test.tracker.entity.UserEntity;
import com.test.tracker.exception.NotFoundException;
import com.test.tracker.exception.TaskTrackerException;
import com.test.tracker.exception.handler.ErrorReason;
import com.test.tracker.mapper.CommentMapper;
import com.test.tracker.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CommentService {

  private final CommentRepository commentRepository;
  private final TaskService taskService;
  private final CommentMapper commentMapper;
  private final UserService userService;

  @Transactional
  public CommentDto createComment(BigInteger taskId, CreateCommentDto createCommentDto) {
    TaskEntity task = taskService.getByIdOrThrow(taskId);
    UserEntity user = userService.getByIdOrThrow(createCommentDto.getUserId());
    CommentEntity comment = commentMapper.toEntity(createCommentDto);
    comment.setTask(task);
    comment.setAuthor(user);
    comment = commentRepository.save(comment);

    return commentMapper.toDto(comment);
  }

  @Transactional
  public void deleteComment(BigInteger taskId, BigInteger commentId) {
    CommentEntity comment = getByIdOrThrow(commentId);
    if (!Objects.equals(comment.getTask().getId(), taskId)) {
      throw new TaskTrackerException(
          ErrorReason.INVALID_TASK, "You are trying to delete a comment in another task");
    }

    commentRepository.deleteById(commentId);
  }

  private CommentEntity getByIdOrThrow(BigInteger id) {
    return commentRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Cannot find comment with id:" + id));
  }
}
