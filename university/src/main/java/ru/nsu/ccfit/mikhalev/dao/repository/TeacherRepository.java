package ru.nsu.ccfit.mikhalev.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.mikhalev.dao.entity.Teacher;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long>  {

    @Query(value = """ 
                    SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
                    FROM university.teacher WHERE
                         first_name = :firstName AND
                         middle_name = :middleName AND
                         last_name = :lastName
           """, nativeQuery = true)
    boolean existsTeacherByNames(@Param("firstName") String firstName,
                                 @Param("middleName") String middleName,
                                 @Param("lastName") String lastName);


    @Query(value = """
                    SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
                    FROM university.schedule s
                    JOIN university.teacher t
                    ON t.id = s.teacher_id
                    WHERE t.last_name = :last_name 
                    AND t.middle_name = :middle_name 
                    AND t.first_name = :first_name
                    AND s.lesson_delivery = :lesson_delivery
        """, nativeQuery = true)
    boolean exitLessonDelivery(@Param("last_name") String lastName,
                               @Param("middle_name") String middleName,
                               @Param("first_name") String firstName,
                               @Param(value = "lesson_delivery") Instant lessonDelivery);

    @Query(value = """
                    SELECT t.* 
                    FROM university.teacher t
                    WHERE t.last_name = :last_name 
                    AND t.middle_name = :middle_name 
                    AND t.first_name = :first_name
        """, nativeQuery = true)
    Optional<Teacher> findByFullName(@Param("last_name") String lastName,
                                     @Param("middle_name") String middleName,
                                     @Param("first_name") String firstName);

    @Query(value = """
               SELECT t.*
               FROM  university.teacher t
           """, nativeQuery = true)
    List<Teacher> findAllTeacher();
}