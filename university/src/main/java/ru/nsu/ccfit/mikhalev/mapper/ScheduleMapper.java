package ru.nsu.ccfit.mikhalev.mapper;

import lombok.AllArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nsu.ccfit.mikhalev.dao.entity.Group;
import ru.nsu.ccfit.mikhalev.dao.entity.Schedule;
import ru.nsu.ccfit.mikhalev.dao.repository.GroupRepository;
import ru.nsu.ccfit.mikhalev.dto.ScheduleDto;

import java.util.Objects;
import java.util.Set;
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
