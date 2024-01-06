package com.example.smartschool.mappers;

import com.example.smartschool.dto.MarkDto;
import com.example.smartschool.models.Mark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MarkMapper {
    @Mapping(target = "value", source = "dto.value")
    @Mapping(target = "subjectName", source = "dto.subjectName")
    Mark convertDtoToEntity(MarkDto dto, Long id);
}
