package com.test.tracker.repository;

import com.test.tracker.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CommentRepository extends JpaRepository<CommentEntity, BigInteger> {
}
