package com.example.studentdemo.service.mapper;

import com.example.studentdemo.dto.SubjectDTO;
import com.example.studentdemo.model.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface SubjectMapper extends EntityMapper <SubjectDTO, Subject> {
}
