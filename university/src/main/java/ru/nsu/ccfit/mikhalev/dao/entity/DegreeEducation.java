package ru.nsu.ccfit.mikhalev.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

import static ru.nsu.ccfit.mikhalev.context.ValidationEntityContext.MAX_SIZE_DEGREE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "degree_education", schema = "university")
@Entity
public class DegreeEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "degree", nullable = false, length = MAX_SIZE_DEGREE)
    private String degree;

    @ManyToMany(mappedBy = "degreeEducation")
    private Set<Teacher> teachers;
}