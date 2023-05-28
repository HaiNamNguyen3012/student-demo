package com.example.studentdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "student_subject")
@Getter
@Setter
@ToString
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Long studentID;

    private Long subjectID;
}
