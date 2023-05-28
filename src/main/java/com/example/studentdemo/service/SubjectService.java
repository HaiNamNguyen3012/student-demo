package com.example.studentdemo.service;

import com.example.studentdemo.common.CommonStatus;
import com.example.studentdemo.common.ResourceNotFoundException;
import com.example.studentdemo.dto.StudentDTO;
import com.example.studentdemo.dto.SubjectDTO;
import com.example.studentdemo.model.Student;
import com.example.studentdemo.model.StudentSubject;
import com.example.studentdemo.model.Subject;
import com.example.studentdemo.repository.StudentSubjectRepository;
import com.example.studentdemo.repository.SubjectRepository;
import com.example.studentdemo.service.mapper.SubjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentSubjectRepository studentSubjectRepository;

    private final SubjectMapper subjectMapper;

    //Get subject by id
    public Subject getSubjectByID(Long id){
        Optional<Subject> subject = subjectRepository.findById(id);
        if(!subject.isPresent()) throw new ResourceNotFoundException("Không tồn tại môn học");
        SubjectDTO subjectDTO = subjectMapper.toDTO(subject.get());
        return subject.get();
    }

    //Get all subject
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

//    //Delete subject
//    public CommonStatus deleteSubject(Long id){
//        subjectRepository.findById(id);
//
//        subjectRepository.deleteById(id);
//
//        CommonStatus commonStatus = new CommonStatus();
//        commonStatus.setStatus("200");
//        commonStatus.setResponse("200");
//        return commonStatus;
//    }

//    //Save Subject
//    public CommonStatus saveSubject(SubjectDTO subjectDTO){
//        //Lưu thông tin của môn học
//        Subject subject = new Subject();
//        subject.setName(subjectDTO.getName());
//        subject.setStartTime(subjectDTO.getStartTime());
//        subject.setEndTime(subjectDTO.getEndTime());
//        subject.setTeacherName(subjectDTO.getTeacherName());
//        subject.setNumberOfCredit(subjectDTO.getNumberOfCredit());
//
//        Subject savedSubject = subjectRepository.save(subject);
//        //Lưu thông tin sinh viên học môn học
//        List<StudentSubject>studentSubjects = new ArrayList<>();
//        if(subjectDTO.getStudents()!=null){
//            for(Student student : subjectDTO.getStudents()){
//                StudentSubject studentSubject = new StudentSubject();
//                studentSubject.setSubjectID(savedSubject.getId());
//                studentSubject.setStudentID(student.getId());
//                studentSubjects.add(studentSubject);
//            }
//            studentSubjectRepository.saveAll(studentSubjects);
//        }
//        CommonStatus commonStatus = new CommonStatus();
//        commonStatus.setStatus("200");
//        commonStatus.setResponse("200");
//        return commonStatus;
//    }
//
//    //update student
//    public CommonStatus updateSubject(SubjectDTO subjectDTO){
//        Subject existingSubject = subjectRepository.findById(subjectDTO.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + subjectDTO.getId()));
//
//        //Cập nhật thông tin cho môn học
//        existingSubject.setName(subjectDTO.getName());
//        existingSubject.setStartTime(subjectDTO.getStartTime());
//        existingSubject.setEndTime(subjectDTO.getEndTime());
//        existingSubject.setTeacherName(subjectDTO.getTeacherName());
//        existingSubject.setNumberOfCredit(subjectDTO.getNumberOfCredit());
//        Subject savedSubject = subjectRepository.save(existingSubject);
//        //Cập nhật thông tin sinh viên của môn học
//        List<StudentSubject>studentSubjects = new ArrayList<>();
//        if(subjectDTO.getStudents()!=null){
//            for(Student student : subjectDTO.getStudents()){
//                StudentSubject studentSubject = new StudentSubject();
//                studentSubject.setSubjectID(savedSubject.getId());
//                studentSubject.setStudentID(student.getId());
//                studentSubjects.add(studentSubject);
//            }
//            studentSubjectRepository.deleteStudentSubjectBySubjectID(existingSubject.getId());
//            studentSubjectRepository.saveAll(studentSubjects);
//        }
//        CommonStatus commonStatus = new CommonStatus();
//        commonStatus.setStatus("200");
//        commonStatus.setResponse("200");
//        return commonStatus;
//    }
}
