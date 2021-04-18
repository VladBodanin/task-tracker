package com.test.tracker.repository;

import com.test.tracker.entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AttachmentRepository extends JpaRepository<AttachmentEntity, BigInteger> {}
