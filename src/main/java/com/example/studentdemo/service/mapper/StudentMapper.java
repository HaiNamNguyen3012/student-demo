package com.example.studentdemo.service.mapper;

import com.example.studentdemo.dto.StudentDTO;
import com.example.studentdemo.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {
}
