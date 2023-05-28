package com.example.studentdemo.repository;

import com.example.studentdemo.model.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM student_subject WHERE studentID")
    void deleteStudentSubjectByStudentID(@Param("id") Long id);

//    @Modifying
//    @Query(nativeQuery = true, value = "DELETE FROM student_subject WHERE  subjectID")
//    void deleteStudentSubjectBySubjectID(@Param("id") Long id);
}