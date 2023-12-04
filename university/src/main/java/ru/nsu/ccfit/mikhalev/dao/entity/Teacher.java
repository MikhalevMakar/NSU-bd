package ru.nsu.ccfit.mikhalev.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

import static ru.nsu.ccfit.mikhalev.context.ValidationEntityContext.*;

@Setter
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "teacher", schema = "university",
       uniqueConstraints = @UniqueConstraint(columnNames = {"first_name", "middle_name", "last_name"}))
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Pattern(regexp = VALID_NAME, message = "api-university.exception.field.title.description")
    @Column(name = "first_name", nullable = false, length = MAX_SIZE_FIRST_NAME)
    private String firstName;

    @Pattern(regexp = VALID_NAME, message = "api-university.exception.field.title.description")
    @Column(name = "middle_name", nullable = false, length = MAX_SIZE_MIDDLE_NAME)
    private String middleName;

    @Pattern(regexp = VALID_NAME, message = "api-university.exception.field.title.description")
    @Column(name = "last_name", nullable = false, length = MAX_SIZE_LAST_NAME)
    private String lastName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "teacher_degree",
               joinColumns = @JoinColumn(name = "teacher_id"), schema = "university",
               inverseJoinColumns = @JoinColumn(name = "degree_id"))
    private Set<DegreeEducation> degreeEducation = new HashSet<>();
}