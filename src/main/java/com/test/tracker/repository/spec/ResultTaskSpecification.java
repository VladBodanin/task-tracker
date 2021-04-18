package com.test.tracker.repository.spec;

import com.test.tracker.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ResultTaskSpecification implements Specification<TaskEntity> {

  private final Set<Specification<TaskEntity>> specifications;

  @Override
  public Predicate toPredicate(
      Root<TaskEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    return criteriaBuilder.and(
        specifications.stream()
            .map(e -> e.toPredicate(root, query, criteriaBuilder))
            .collect(Collectors.toList())
            .toArray(Predicate[]::new));
  }
}
