package ru.nsu.ccfit.mikhalev.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.mikhalev.dao.entity.DegreeEducation;

import java.util.Optional;

@Repository
public interface DegreeRepository extends CrudRepository<DegreeEducation, Long> {
    @Query(value = """
                    SELECT * FROM university.degree_education
                    WHERE degree ILIKE :degree || '%'
                    UNION
                    SELECT * FROM university.degree_education
                    WHERE degree ILIKE '%' || :degree || '%'
           """, nativeQuery = true)

    Optional<DegreeEducation> findByDegree(@Param("degree") String degree);
}