package com.example.studentdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
public class Student {
    //Khai báo các trường của Student
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String sex;

    private Instant birthday;

    private int grade;

    private String className;

    private String countryside;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_subject",
        joinColumns = @JoinColumn(name = "studentID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subjectID", referencedColumnName = "id")
    )
    private Set<Subject> subjects;
}
