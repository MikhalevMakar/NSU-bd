package ru.nsu.ccfit.mikhalev.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.ccfit.mikhalev.dao.entity.*;

import java.time.Instant;
import java.util.*;

public interface ScheduleRepository extends CrudRepository<Schedule, ScheduleId> {

    @Query(value = """
                    SELECT s FROM univevrsity.schedule AS s
                    WHERE s.lessonDelivery = :lesson_delivery
                    AND s.groups.number IN :groups
        """, nativeQuery = true)
    Optional<Schedule> findByGroupsAndLessonDelivery(@Param(value = "groups") Set<Long> groups,
                                                     @Param(value = "lesson_delivery") Instant lessonDelivery);
}
