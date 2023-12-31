package ru.nsu.ccfit.mikhalev.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.ccfit.mikhalev.dao.entity.Subject;

import java.util.Optional;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

    @Query(value = """
                    SELECT * FROM university.subject AS s
                    WHERE s.title ILIKE '%' ||  :title || '%'
          """, nativeQuery = true)
    Optional<Subject> findByTitle(@Param("title") String title);
}
