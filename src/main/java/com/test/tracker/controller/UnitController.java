package com.test.tracker.controller;

import com.test.tracker.dto.CreateUnitDto;
import com.test.tracker.dto.UnitDto;
import com.test.tracker.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/units")
public class UnitController {

  private final UnitService unitService;

  @PostMapping
  public ResponseEntity<UnitDto> createUnit(@Valid @RequestBody CreateUnitDto unitDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(unitService.createUnit(unitDto));
  }
}
