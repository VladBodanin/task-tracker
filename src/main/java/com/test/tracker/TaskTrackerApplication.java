package com.test.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.test.tracker.*")
public class TaskTrackerApplication {
  public static void main(String[] args) {
    SpringApplication.run(TaskTrackerApplication.class, args);
  }
}
