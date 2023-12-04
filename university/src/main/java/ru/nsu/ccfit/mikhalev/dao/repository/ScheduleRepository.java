package ru.nsu.ccfit.mikhalev.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.ccfit.mikhalev.dao.entity.*;
import ru.nsu.ccfit.mikhalev.dto.TeacherScheduleDto;

import java.time.Instant;
import java.util.*;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    @Query(value = """
                SELECT s.* FROM university.schedule s
                JOIN university.group_number g
                ON g.schedule_id = s.id
                WHERE s.lesson_delivery = :lesson_delivery
                AND g.number IN :groups
                LIMIT 1
            """, nativeQuery = true)
    Optional<Schedule> findByGroupsAndLessonDelivery(@Param(value = "groups") Set<Long> groups,
                                                     @Param(value = "lesson_delivery") Instant lessonDelivery);


    @Query("""
            SELECT new ru.nsu.ccfit.mikhalev.dto.TeacherScheduleDto(s.lessonDelivery, su.title, sg.number) 
            FROM Schedule s 
            JOIN s.teacher t 
            JOIN s.subject su 
            LEFT JOIN s.groups sg
                ON s = sg.schedule
            WHERE t.firstName = :first AND
                  t.middleName = :middle AND 
                  t.lastName = :last
            """)
    List<TeacherScheduleDto> findAllScheduleByTeacher(@Param("first") String firstName,
                                                      @Param("middle") String middleName,
                                                      @Param("last") String lastName);
}
