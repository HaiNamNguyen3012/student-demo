package com.example.studentdemo.controller;


import com.example.studentdemo.common.CommonStatus;
import com.example.studentdemo.common.ResourceNotFoundException;
import com.example.studentdemo.dto.StudentDTO;
import com.example.studentdemo.dto.StudentSubjectDTO;
import com.example.studentdemo.model.Student;
import com.example.studentdemo.model.Subject;
import com.example.studentdemo.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    //đk môn học mới cho sinh viên

    @PostMapping
    public ResponseEntity<CommonStatus> saveStudentSubject(@RequestBody StudentSubjectDTO studentSubjectDTO){
        if(studentSubjectDTO.getId() != null) throw new ResourceNotFoundException("Tạo môn học mới không được có id");
        return ResponseEntity.ok(adminService.saveStudentSubject(studentSubjectDTO));
    }

    //Xóa môn học đăng ký của học sinh
    @DeleteMapping("")
    public ResponseEntity<CommonStatus> deleteStudentSubject(@RequestParam Long studentID, @RequestParam Long subjectID){
        return ResponseEntity.ok(adminService.deleteStudentSubject(studentID, subjectID));
    }

    //Xem list môn học đăng ký của học sinh
    @GetMapping("/{studentID}/subjects")
    public ResponseEntity<Set<Subject>> getSubjectsOfStudent(@PathVariable Long studentID){
        return ResponseEntity.ok(adminService.getSubjectsByStudentID(studentID));
    }

    //Xem danh sách học sinh đăng ký 1 môn học
    @GetMapping("/{subjectID}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsOfSubject(@PathVariable Long subjectID){
        return ResponseEntity.ok(adminService.getAllStudentsBySubjectID(subjectID));
    }
    //Thống kê số môn học có nhiều học sinh đăng ký nhất và số học sinh đăng ký môn học đấy
}
