package com.example.studentdemo.controller;


import com.example.studentdemo.common.CommonStatus;
import com.example.studentdemo.common.ResourceNotFoundException;
import com.example.studentdemo.dto.StudentSubjectDTO;
import com.example.studentdemo.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping
    public ResponseEntity<CommonStatus> deleteStudentSubject(@PathVariable Long studentID, @PathVariable Long subjectID){
        return ResponseEntity.ok(adminService.deleteStudentSubject(studentID, subjectID));
    }

    //Xem list môn học đăng ký của học sinh

    //Xem danh sách học sinh đăng ký 1 môn học

    //Thống kê số môn học có nhiều học sinh đăng ký nhất và số học sinh đăng ký môn học đấy
}
