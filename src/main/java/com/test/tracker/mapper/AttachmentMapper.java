package com.test.tracker.mapper;

import com.test.tracker.dto.AttachmentDto;
import com.test.tracker.entity.AttachmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttachmentMapper {

    AttachmentEntity toEntity(AttachmentDto attachmentDto);

    AttachmentDto toDto(AttachmentEntity attachmentEntity);

}
