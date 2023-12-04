package ru.nsu.ccfit.mikhalev.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"groups", "subject", "teacher"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="schedule", schema = "university")
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Group> groups;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @Column(name = "lesson_delivery", nullable = false)
    private Instant lessonDelivery;
}