package com.example.studentdemo.controller;

import com.example.studentdemo.common.CommonStatus;
import com.example.studentdemo.common.ResourceNotFoundException;
import com.example.studentdemo.dto.StudentDTO;
import com.example.studentdemo.model.Student;
import com.example.studentdemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    //Get Student By ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentByID(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentByID(id));
    }

    //Get All Student
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    //Create Student
    @PostMapping
    public ResponseEntity<CommonStatus> saveStudent(@RequestBody StudentDTO studentDTO){
        if(studentDTO.getId() != null) throw new ResourceNotFoundException("Tạo mới không được có id");
        return ResponseEntity.ok(studentService.saveStudent(studentDTO));
    }

    //Update Student
    @PutMapping("")
    public ResponseEntity<CommonStatus> updateStudent(@RequestBody StudentDTO studentDTO){
        if(studentDTO.getId() == null) throw new ResourceNotFoundException("Bắt buộc phải có ID");
        return ResponseEntity.ok(studentService.updateStudent(studentDTO));
    }

    //Delete Student
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonStatus> deleteStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
}
