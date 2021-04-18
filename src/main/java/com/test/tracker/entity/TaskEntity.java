package com.test.tracker.entity;

import com.test.tracker.dto.TaskState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(of = "id")
@Data
@Entity
@Table(name = "tasks")
public class TaskEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  @Column private Instant createdAt;
  @Column private String topic;
  @Column private String description;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "author_id")
  private UserEntity author;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "performer_id")
  private UserEntity performer;

  @Column
  @Enumerated(EnumType.STRING)
  private TaskState state;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "unit_id")
  private UnitEntity unit;

  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
  @Column
  private List<CommentEntity> comments;

  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
  @Column
  private List<AttachmentEntity> attachments;
}
