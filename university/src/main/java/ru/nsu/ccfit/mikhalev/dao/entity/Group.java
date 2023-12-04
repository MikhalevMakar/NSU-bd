package ru.nsu.ccfit.mikhalev.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static ru.nsu.ccfit.mikhalev.context.ValidationEntityContext.SIZE_VALID_GROUP;

@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Table(name = "group_number", schema = "university")
public class Group {

    @Id
    @Size(min = SIZE_VALID_GROUP, max = SIZE_VALID_GROUP,
          message = "api-university.exception.constraints.description")
    private Long number;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;
}