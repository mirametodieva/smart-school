package com.example.smartschool.mappers;

import com.example.smartschool.dto.GradeDto;
import com.example.smartschool.models.Grade;
import org.mapstruct.Mapping;

public interface GradeMapper {
    @Mapping(target = "name", source = "dto.name")
    Grade convertDtoToEntity(GradeDto dto, Long id);
}