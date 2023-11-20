package ru.nsu.ccfit.mikhalev.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.mikhalev.dao.entity.Group;

import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Query(value = """
                    SELECT * FROM university.group_number
                    WHERE number = :number
               """, nativeQuery = true)
    Optional<Group> findByNumber(@Param(value = "number") Long number);
}