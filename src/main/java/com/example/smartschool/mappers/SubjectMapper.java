package com.example.smartschool.mappers;

import com.example.smartschool.dto.SubjectDto;
import com.example.smartschool.models.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper {
    @Mapping(target = "name", source = "name")
    Subject convertDtoToEntity(SubjectDto dto, Long id);
}
