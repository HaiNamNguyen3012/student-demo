package com.example.studentdemo.controller;

import com.example.studentdemo.common.CommonStatus;
import com.example.studentdemo.common.ResourceNotFoundException;
import com.example.studentdemo.dto.StudentDTO;
import com.example.studentdemo.dto.SubjectDTO;
import com.example.studentdemo.model.Subject;
import com.example.studentdemo.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subject")
@AllArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    //Get Subject By ID
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectByID(@PathVariable Long id){
        return ResponseEntity.ok(subjectService.getSubjectByID(id));
    }

    //Get All Subject
    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects(){ return ResponseEntity.ok(subjectService.getAllSubjects());}

    //Create Subject
    @PostMapping
    public ResponseEntity<CommonStatus> saveSubject(@RequestBody SubjectDTO subjectDTO){
        if(subjectDTO.getId() != null) throw new ResourceNotFoundException("Tạo mới không được có id");
        return ResponseEntity.ok(subjectService.saveSubject(subjectDTO));
    }

    //Update Subject
    @PutMapping("")
    public ResponseEntity<CommonStatus> updateTeacher(@RequestBody SubjectDTO subjectDTO){
        if(subjectDTO.getId() == null) throw new ResourceNotFoundException("Bắt buộc phải có ID");
        return ResponseEntity.ok(subjectService.updateSubject(subjectDTO));
    }

    //Delete Subject
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonStatus> deleteSubject(@PathVariable Long id){
        return ResponseEntity.ok(subjectService.deleteSubject(id));
    }
}
