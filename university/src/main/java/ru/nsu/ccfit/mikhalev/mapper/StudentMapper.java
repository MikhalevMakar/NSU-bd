package ru.nsu.ccfit.mikhalev.mapper;

import org.mapstruct.Mapper;
import ru.nsu.ccfit.mikhalev.dao.entity.Student;
import ru.nsu.ccfit.mikhalev.dto.StudentDto;

@Mapper
public interface StudentMapper {

    Student mapToEntity(StudentDto dto);
}
