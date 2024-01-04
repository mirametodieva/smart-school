package com.example.smartschool.mappers;

import com.example.smartschool.dto.SubjectDto;
import com.example.smartschool.models.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    @Mapping(target = "name", source = "name")
    Subject convertDtoToEntity(SubjectDto dto, Long id);
}
