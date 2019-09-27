package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CourseControllerTest {

  @Autowired private ApplicationContext applicationContext;
  @Autowired private CourseRepository courseRepository;
  private WebTestClient client;
  private List<Course> expectedProducts;

  @BeforeEach
  void setUp() {
    client =
        WebTestClient.bindToApplicationContext(applicationContext)
            .configureClient()
            .baseUrl("")
            .build();

    Flux<Course> initData =
        courseRepository
            .deleteAll()
            .thenMany(
                Flux.just(
                        Course.builder()
                            .idCourse("I002")
                            .nameCourse("algebra")
                            .status("activo")
                            .quotaMax("5")
                            .quotaMin("3")
                            .build(),
                        Course.builder()
                            .idCourse("I003")
                            .nameCourse("springboot")
                            .status("inactivo")
                            .quotaMax("5")
                            .quotaMin("18")
                            .build())
                    .flatMap(courseRepository::save))
            .thenMany(courseRepository.findAll());

    expectedProducts = initData.collectList().block();
  }

  @Test
  void create() {
    Course expectedProduct = expectedProducts.get(0);

    client
        .post()
        .uri("/")
        .body(Mono.just(expectedProduct), Course.class)
        .exchange()
        .expectStatus()
        .isCreated();
  }

  @Test
  void findAll() {
    client.get().uri("/").exchange().expectStatus().isOk();
  }

  @Test
  void eliminar() {
    Course productToDelete = expectedProducts.get(0);
    client
        .delete()
        .uri("/{id}", productToDelete.getIdCourse())
        .exchange()
        .expectStatus()
        .isNoContent();
  }

  @Test
  void findBynameCourse() {

    String title = "algebra";
    client.get().uri("/name/{title}", title).exchange().expectStatus().isOk();
  }

  @Test
  void findByCourseId() {

    String id = "I002";
    client.get().uri("/{id}", id).exchange().expectStatus().isOk();
  }

  @Test
  void update() {

    Course expectedProduct = expectedProducts.get(0);

    client
        .put()
        .uri("/{id}", expectedProduct.getIdCourse())
        .body(Mono.just(expectedProduct), Course.class)
        .exchange()
        .expectStatus()
        .isOk();
  }
}
