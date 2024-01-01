package com.example.smartschool.mappers;

import com.example.smartschool.dto.StudentDto;
import com.example.smartschool.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    @Mapping(target = "studentNum", source = "dto.studentNum")
    @Mapping(target = "parentPhone", source = "dto.parentPhone")
    Student convertDtoToEntity(StudentDto dto, Long id);
}
