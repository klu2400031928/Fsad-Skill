package com.example.student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Student Entity")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Student ID", example = "1")
    private Long id;

    @NotBlank(message = "Name is required")
    @Schema(description = "Student Name", example = "Rahul")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "Email Address", example = "rahul@gmail.com")
    private String email;

    @NotBlank(message = "Course is required")
    @Schema(description = "Course Name", example = "CSE")
    private String course;

    public Student() {}

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCourse() { return course; }

    public void setCourse(String course) { this.course = course; }
}
