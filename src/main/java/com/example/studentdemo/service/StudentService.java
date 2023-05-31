package com.example.studentdemo.service;

import com.example.studentdemo.common.CommonStatus;
import com.example.studentdemo.common.ResourceNotFoundException;
import com.example.studentdemo.dto.StudentDTO;
import com.example.studentdemo.model.Student;
import com.example.studentdemo.model.StudentSubject;
import com.example.studentdemo.model.Subject;
import com.example.studentdemo.repository.StudentRepository;
import com.example.studentdemo.repository.StudentSubjectRepository;
import com.example.studentdemo.service.mapper.StudentMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentSubjectRepository studentSubjectRepository;

    private final StudentMapper studentMapper;

    //get student by id
    public Student getStudentByID(Long id){
        Optional<Student> student = studentRepository.findById(id);
//        Student student1 = studentRepository.getById(1); k sd
        student.get();
        //Khi lấy dữ liệu trong db, check xem dữ liệu có tồn tại hay không rồi mới dùng hàm get(
        if(!student.isPresent()) throw new ResourceNotFoundException("Không tồn tại sinh viên");
        StudentDTO studentDTO = studentMapper.toDTO(student.get());
        return student.get();
    }

    //get all student
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    //delete student
    public CommonStatus deleteStudent(Long id){
        studentRepository.findById(id);

        studentRepository.deleteById(id);

        CommonStatus commonStatus = new CommonStatus();
        commonStatus.setStatus("200");
        commonStatus.setResponse("200");
        return commonStatus;
    }

    //save student
    public CommonStatus saveStudent(StudentDTO studentDTO){
        Student student = studentMapper.toEntity(studentDTO);
        studentRepository.save(student);
        //Lưu thông tin của sinh viên
//        Student student = new Student();
//        student.setBirthday(studentDTO.getBirthday());
//        student.setClassName(studentDTO.getClassName());
//        student.setCountryside(studentDTO.getCountryside());
//        student.setBirthday(studentDTO.getBirthday());
//        student.setGrade(studentDTO.getGrade());
//        student.setName(studentDTO.getName());
//        student.setSex(studentDTO.getSex());
//        Student savedStudent = studentRepository.save(student);
//
//        //Lưu thông tin môn học của sinh viên
//        List<StudentSubject> studentSubjects = new ArrayList<>();
//        if(studentDTO.getSubjects() != null){
//            for(Subject subject : studentDTO.getSubjects()){
//                StudentSubject studentSubject = new StudentSubject();
//                studentSubject.setStudentID(savedStudent.getId());
//                studentSubject.setSubjectID(subject.getId());
//                studentSubjects.add(studentSubject);
//            }
//            studentSubjectRepository.saveAll(studentSubjects);
//        }
        CommonStatus commonStatus = new CommonStatus();
        commonStatus.setStatus("200");
        commonStatus.setResponse("200");
        return commonStatus;
    }

    //save subject
//    public CommonStatus saveSubject(Student){
//
//    }

    //update student
    public CommonStatus updateStudent(StudentDTO studentDTO){
        Student existingStudent = studentRepository.findById(studentDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentDTO.getId()));

        //Cập nhật thông tin cho student
        existingStudent.setName(studentDTO.getName());
        existingStudent.setGrade(studentDTO.getGrade());
        existingStudent.setCountryside(studentDTO.getCountryside());
        existingStudent.setBirthday(studentDTO.getBirthday());
        existingStudent.setClassName(studentDTO.getClassName());
        existingStudent.setSex(studentDTO.getSex());
        Student savedStudent = studentRepository.save(existingStudent);

        //Cập nhật thông tin môn cho sinh viên
        List<StudentSubject> studentSubjects = new ArrayList<>();
        if(studentDTO.getSubjects() != null){
            for(Subject subject : studentDTO.getSubjects()){
                StudentSubject studentSubject = new StudentSubject();
                studentSubject.setStudentID(savedStudent.getId());
                studentSubject.setSubjectID(subject.getId());
                studentSubjects.add(studentSubject);
            }
            //Xóa môn học cũ thêm môn học mới
            studentSubjectRepository.deleteStudentSubjectByStudentID(existingStudent.getId());
            studentSubjectRepository.saveAll(studentSubjects);
        }

        CommonStatus commonStatus = new CommonStatus();
        commonStatus.setResponse("200");
        commonStatus.setStatus("200");
        return commonStatus;
    }
}
