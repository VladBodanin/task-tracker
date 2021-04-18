package com.test.tracker.repository;

import com.test.tracker.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface TaskRepository
    extends JpaRepository<TaskEntity, BigInteger>, JpaSpecificationExecutor {}
