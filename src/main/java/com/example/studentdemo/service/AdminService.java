package com.example.studentdemo.service;

import com.example.studentdemo.common.CommonStatus;
import com.example.studentdemo.dto.StudentSubjectDTO;
import com.example.studentdemo.model.StudentSubject;
import com.example.studentdemo.repository.StudentSubjectRepository;
import com.example.studentdemo.service.mapper.StudentSubjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AdminService {
    private final StudentSubjectRepository studentSubjectRepository;

    private final StudentSubjectMapper studentSubjectMapper;

    //Đăng ký môn học mới cho sinh viên
    public CommonStatus saveStudentSubject(StudentSubjectDTO studentSubjectDTO){
        StudentSubject studentSubject = studentSubjectMapper.toEntity(studentSubjectDTO);
        studentSubjectRepository.save(studentSubject);

        CommonStatus commonStatus = new CommonStatus();
        commonStatus.setStatus("200");
        commonStatus.setResponse("200");

        return commonStatus;
    }

}
