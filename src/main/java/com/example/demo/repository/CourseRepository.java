package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CourseRepository extends ReactiveMongoRepository<Course, String> {

  Mono<Course> findBynameCourse(String name);
}
