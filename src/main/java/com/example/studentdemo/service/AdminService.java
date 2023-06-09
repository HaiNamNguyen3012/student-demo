package com.example.studentdemo.service;

import com.example.studentdemo.common.CommonStatus;
import com.example.studentdemo.common.ResourceNotFoundException;
import com.example.studentdemo.dto.StudentDTO;
import com.example.studentdemo.dto.StudentSubjectDTO;
import com.example.studentdemo.model.Student;
import com.example.studentdemo.model.StudentSubject;
import com.example.studentdemo.model.Subject;
import com.example.studentdemo.repository.StudentRepository;
import com.example.studentdemo.repository.StudentSubjectRepository;
import com.example.studentdemo.service.mapper.StudentSubjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class AdminService {
    private final StudentSubjectRepository studentSubjectRepository;
    private final StudentRepository studentRepository;

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

    //Xóa môn học đăng ký của sinh viên
    public CommonStatus deleteStudentSubject(Long studentID, Long subjectID){
        studentSubjectRepository.deleteByStudentIDAndSubjectID(studentID, subjectID);

        CommonStatus commonStatus = new CommonStatus();
        commonStatus.setStatus("200");
        commonStatus.setResponse("200");

        return commonStatus;
    }


    //Lấy danh sách môn học của sinh viên
    public Set<Subject> getSubjectsByStudentID(Long studentID){
        Optional<Student> optionalStudent = studentRepository.findById(studentID);
        if(!optionalStudent.isPresent()) throw new ResourceNotFoundException("Không tìm thấy sinh viên");
        Student student = optionalStudent.get();
        return student.getSubjects();
    }

    //Lấy danh sách sinh viên của môn học
    public List<StudentDTO> getAllStudentsBySubjectID(Long subjectID){
        //Tìm list studentID dựa vào subjectID
        List<Student> students = new ArrayList<>();
        List<Long> studentids = studentSubjectRepository.studentId(subjectID);
        for (Long studentid : studentids) {
            //Tìm Student dựa vào StudentID
            Optional<Student> student = studentRepository.findById(studentid);
            //check xem có tồn tại hay k
            students.add(student.get());
        }
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for(Student student: students){
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setBirthday(student.getBirthday());
            studentDTO.setClassName(student.getClassName());
            studentDTO.setCountryside(student.getCountryside());
            studentDTO.setBirthday(student.getBirthday());
            studentDTO.setGrade(student.getGrade());
            studentDTO.setName(student.getName());
            studentDTO.setSex(student.getSex());
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }
}
