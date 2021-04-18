package com.test.tracker.repository;

import com.test.tracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, BigInteger> {
  Optional<UserEntity> findByName(String name);
}
