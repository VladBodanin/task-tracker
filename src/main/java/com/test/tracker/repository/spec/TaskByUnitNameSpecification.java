package com.test.tracker.repository.spec;

import com.test.tracker.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class TaskByUnitNameSpecification implements Specification<TaskEntity> {

  private final String unitName;

  @Override
  public Predicate toPredicate(
      Root<TaskEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    return criteriaBuilder.equal(root.get("unit").get("name"), unitName);
  }
}
