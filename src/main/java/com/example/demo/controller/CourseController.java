package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.ServiceCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping
public class CourseController {

  @Autowired private ServiceCourse serviceCourse;

  @PostMapping("/")
  public Mono<ResponseEntity<Course>> create(@Valid @RequestBody Course student) {

    return serviceCourse
        .save(student)
        .map(
            p ->
                ResponseEntity.created(URI.create("/".concat(p.getIdCourse())))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(p));
  }

  @GetMapping("/")
  public Mono<ResponseEntity<Flux<Course>>> findAll() {

    return Mono.just(
        ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(serviceCourse.findAll()));
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id) {
    return serviceCourse
        .findById(id)
        .flatMap(
            p ->
                serviceCourse
                    .delete(p)
                    .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
        .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  }



  @GetMapping("/{id}")
  public Mono<ResponseEntity<Course>> findByCourseId(@PathVariable String id) {

    return serviceCourse
        .findById(id)
        .map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p));
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<Course>> update(@RequestBody Course course, @PathVariable String id) {
    return serviceCourse
        .findById(id)
        .flatMap(
            p -> {
              p.setNameCourse(course.getNameCourse());
              p.setStatus(course.getStatus());
              p.setQuotaMax(course.getQuotaMax());
              p.setQuotaMin(course.getQuotaMin());
              p.setNota1(course.getNota1());
              p.setNota2(course.getNota2());
              p.setNota3(course.getNota3());
              p.setDocument(course.getDocument());
              p.setDniteacher(course.getDniteacher());

              return serviceCourse.save(p);
            })
        .map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
