package ru.nsu.ccfit.mikhalev.dao.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static ru.nsu.ccfit.mikhalev.context.ValidationEntityContext.*;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="subject", schema = "university")
@Entity
public class Subject {
    @Id
    @EqualsAndHashCode.Exclude
    private Long id;

    @Pattern(regexp = VALID_NAME, message = "api-university.exception.field.title.description")
    @Column(nullable = false, length = MAX_SIZE_SUBJECT)
    private String title;
}
