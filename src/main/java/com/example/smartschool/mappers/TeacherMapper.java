package com.example.smartschool.mappers;

import com.example.smartschool.dto.TeacherDto;
import com.example.smartschool.models.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    @Mapping(target = "teacherNum", source = "dto.teacherNum")
    @Mapping(target = "education", source = "dto.education")
    Teacher convertDtoToEntity(TeacherDto dto, Long id);
}
