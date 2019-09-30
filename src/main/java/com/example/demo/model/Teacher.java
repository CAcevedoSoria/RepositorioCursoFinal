package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Teacher {

	@Id
	private String idTeacher;
	private String nameTeacher;
	private String age;
	private String dictatedCourses;
	private String dniteacher;

}
