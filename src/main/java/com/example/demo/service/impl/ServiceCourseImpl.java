package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.ServiceCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceCourseImpl implements ServiceCourse {

  @Autowired private CourseRepository courseRepository;

  @Override
  public Flux<Course> findAll() {
    return courseRepository.findAll();
  }

  @Override
  public Mono<Course> findById(String id) {
    return courseRepository.findById(id);
  }

  public Mono<Course> save(Course producto) {
    return courseRepository.save(producto);
  }

  @Override
  public Mono<Void> delete(Course student) {
    return courseRepository.delete(student);
  }

  @Override
  public Mono<Course> findBynameCourse(String name) {
    return courseRepository.findBynameCourse(name);
  }
}
