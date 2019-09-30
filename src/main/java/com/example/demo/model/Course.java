package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Course2")
public class Course {

  @Id private String idCourse;
    private String nameCourse;
  private String status;
  private String quotaMin;
  private String quotaMax;
  private Double nota1;
  private Double nota2;
  private Double nota3;
  private String document;
  private String dniteacher;



}
