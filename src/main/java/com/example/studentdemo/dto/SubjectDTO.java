package com.example.studentdemo.dto;

import com.example.studentdemo.model.Student;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class SubjectDTO {
    //Khai báo các trường của subjectdto
    private Long id;

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int numberOfCredit;

    private String teacherName;

    private Set<Student> students;
}
