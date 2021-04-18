package com.test.tracker.mapper;

import com.test.tracker.dto.CommentDto;
import com.test.tracker.dto.CreateCommentDto;
import com.test.tracker.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentEntity toEntity(CreateCommentDto commentDto);

    CommentDto toDto(CommentEntity commentEntity);
}
