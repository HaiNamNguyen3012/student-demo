package com.example.studentdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "subject")
@Getter
@Setter
@ToString
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int numberOfCredit;

    private String teacherName;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "student_subject",
//            joinColumns = @JoinColumn(name = "subjectID", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "studentID", referencedColumnName = "id")
//    )

}
