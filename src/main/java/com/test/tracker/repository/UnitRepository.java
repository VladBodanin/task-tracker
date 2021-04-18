package com.test.tracker.repository;

import com.test.tracker.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UnitRepository extends JpaRepository<UnitEntity, BigInteger> {
  Optional<UnitEntity> findByName(String name);
}
