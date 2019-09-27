package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ServiceCourseImplTest {

  @Mock private CourseRepository courseRepository;

  @InjectMocks private ServiceCourseImpl serviceCourse;

  @Test
  void findAll() {
    Course course = new Course();
    course.setIdCourse("C012");
    course.setNameCourse("Algebra");
    course.setStatus("activo");
    course.setQuotaMax("20");
    course.setQuotaMin("2");

    when(serviceCourse.findAll()).thenReturn(Flux.just(course));

    Flux<Course> actual = serviceCourse.findAll();

    assertResults(actual, course);
  }

  @Test
  void findById() {
    Course course = new Course();
    course.setIdCourse("C012");
    course.setNameCourse("Algebra");
    course.setStatus("activo");
    course.setQuotaMax("20");
    course.setQuotaMin("2");

    when(courseRepository.findById(course.getIdCourse())).thenReturn(Mono.just(course));
    Mono<Course> actual = serviceCourse.findById(course.getIdCourse());

    assertResults(actual, course);
  }

  @Test
  void save() {
    Course course = new Course();
    course.setIdCourse("C012");
    course.setNameCourse("Algebra");
    course.setStatus("activo");
    course.setQuotaMax("20");
    course.setQuotaMin("2");

    when(courseRepository.save(course)).thenReturn(Mono.just(course));

    Mono<Course> actual = serviceCourse.save(course);

    assertResults(actual, course);
  }

  @Test
  void delete() {

    Course course = new Course();
    course.setIdCourse("C012");
    course.setNameCourse("Algebra");
    course.setStatus("activo");
    course.setQuotaMax("20");
    course.setQuotaMin("2");
    when(courseRepository.delete(course)).thenReturn(Mono.empty());
  }

  @Test
  void findBynameCourse() {

    Course course = new Course();
    course.setIdCourse("C012");
    course.setNameCourse("Algebra");
    course.setStatus("activo");
    course.setQuotaMax("20");
    course.setQuotaMin("2");
    when(courseRepository.findBynameCourse("Algebra")).thenReturn(Mono.just(course));

    Mono<Course> actual = serviceCourse.findBynameCourse("Algebra");

    assertResults(actual, course);
  }

  private void assertResults(Publisher<Course> publisher, Course... expectedProducts) {
    StepVerifier.create(publisher).expectNext(expectedProducts).verifyComplete();
  }
}
