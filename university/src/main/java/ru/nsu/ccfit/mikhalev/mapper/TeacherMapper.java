package ru.nsu.ccfit.mikhalev.mapper;

import org.mapstruct.Mapper;
import ru.nsu.ccfit.mikhalev.dao.entity.Teacher;
import ru.nsu.ccfit.mikhalev.dto.TeacherDto;

@Mapper
public interface TeacherMapper {
    Teacher mapToEntity(TeacherDto dto);
}
