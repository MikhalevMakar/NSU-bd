package ru.nsu.ccfit.mikhalev.mapper;


import org.mapstruct.*;
import ru.nsu.ccfit.mikhalev.dao.entity.*;
import ru.nsu.ccfit.mikhalev.dto.TeacherDto;

import java.util.Set;

@Mapper
public interface TeacherMapper {

    Teacher mapToEntity(TeacherDto dto);

    @Mapping(source = "degreeEducation", target = "degree", qualifiedByName = "mapDegree")
    TeacherDto mapToDto(Teacher entity);

    @Named("mapDegree")
    default String mapDegree(Set<DegreeEducation> degrees) {
        return degrees.isEmpty() ?
                                  "No degrees available"
                                  :
                                  degrees.iterator().next().getDegree();
    }
}
