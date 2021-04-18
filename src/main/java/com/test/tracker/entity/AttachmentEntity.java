package com.test.tracker.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigInteger;

@EqualsAndHashCode(of = "id")
@Data
@Entity
@Table(name = "attachments")
public class AttachmentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  @Column String downloadLink;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "task_id")
  TaskEntity task;
}
