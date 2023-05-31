package com.example.studentdemo.service.mapper;

import com.example.studentdemo.dto.StudentSubjectDTO;
import com.example.studentdemo.model.StudentSubject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface StudentSubjectMapper extends EntityMapper<StudentSubjectDTO, StudentSubject>{
}
