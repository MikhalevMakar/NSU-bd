package ru.nsu.ccfit.mikhalev.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.util.Set;

import lombok.experimental.FieldDefaults;
import static ru.nsu.ccfit.mikhalev.context.ValidationEntityContext.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"numberPhone", "averageScore"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "student",
       schema = "university",
       uniqueConstraints = @UniqueConstraint(columnNames = {"first_name", "middle_name", "last_name"}))
public class Student {

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

    @Column(name = "average_score", nullable = false)
    @Check(constraints = "average_score >= 0 AND average_score <= 100")
    private BigDecimal averageScore;

    @Check(constraints =  "number_phone ~ '^[0-9]{11}$'")
    @Column(name = "number_phone", nullable = false, length = SIZE_NUMBER_PHONE)
    private String numberPhone;

    @ManyToOne(cascade = CascadeType.ALL)
    private Group group;
}