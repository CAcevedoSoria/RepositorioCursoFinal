package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Setter
@Getter
public class Student {
	@Id
	private String id;
	private String fullName;
	@NotEmpty
	private String gender;

	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate birthday;
	private String address;
	private String academicPeriod;
	private String typeDocument;
	@Size(min = 8, max = 8)
	private String document;
}
