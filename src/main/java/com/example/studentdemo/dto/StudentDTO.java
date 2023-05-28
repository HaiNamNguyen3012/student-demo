package com.example.studentdemo.dto;

import com.example.studentdemo.model.Subject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Data
public class StudentDTO {
    //Khai báo các trường của studentdto
    private Long id;

    private String name;

    private String sex;

    private Instant birthday;

    private int grade;

    private String className;

    private String countryside;

    private Set<Subject> subjects;
}
