package ru.nsu.ccfit.mikhalev.mapper;

import org.mapstruct.*;
import ru.nsu.ccfit.mikhalev.dao.entity.*;
import ru.nsu.ccfit.mikhalev.dao.repository.GroupRepository;
import ru.nsu.ccfit.mikhalev.dto.ScheduleDto;

import java.util.*;
import java.util.stream.Collectors;

@Mapper
public interface ScheduleMapper {
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "groups", qualifiedByName = "mapGroups")
    Schedule mapToEntity(ScheduleDto dto, @Context GroupRepository groupRepository);

    @Named("mapGroups")
    default Set<Group> mapGroups(Set<Long> groups, @Context GroupRepository groupRepository) {
        return groups.stream()
            .map(groupId -> groupRepository.findById(groupId).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }

}
