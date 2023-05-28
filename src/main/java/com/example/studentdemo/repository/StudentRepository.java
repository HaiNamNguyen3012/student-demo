package com.example.studentdemo.repository;

import com.example.studentdemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM  student_subject where studentID = :id\n")
    void deleteStudentSubjectByStudent_id(@Param("id") Long id);
}
