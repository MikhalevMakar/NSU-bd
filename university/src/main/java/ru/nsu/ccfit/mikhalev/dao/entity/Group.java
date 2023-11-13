package ru.nsu.ccfit.mikhalev.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static ru.nsu.ccfit.mikhalev.context.ValidationEntityContext.SIZE_VALID_GROUP;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Table(name = "group", schema = "university")
public class Group {

    @Id
    @Size(min = SIZE_VALID_GROUP, max = SIZE_VALID_GROUP,
          message = "api-university.exception.constraints.description")
    private Long number;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Schedule schedule;
}