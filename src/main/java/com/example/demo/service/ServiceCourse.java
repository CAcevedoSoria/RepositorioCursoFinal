package com.example.demo.service;

import com.example.demo.model.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServiceCourse {

  public Flux<Course> findAll();

  public Mono<Course> findById(String id);

  public Mono<Course> save(Course course);

  public Mono<Void> delete(Course course);

  public Mono<Course> findBynameCourse(String name);

  /**
   * Find full name mono.
   *
   * @param name the name
   * @return the mono
   */
}
