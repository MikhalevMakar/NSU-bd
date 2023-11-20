package ru.nsu.ccfit.mikhalev.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"group", "subject", "teacher"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="schedule", schema = "university")
@Entity
public class Schedule {

    @EmbeddedId
    private ScheduleId id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Group> groups;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false,
                foreignKey = @ForeignKey(name = "subject_id"))
        private Subject subject;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false,
                foreignKey = @ForeignKey(name = "teacher_id"))
    private Teacher teacher;

    @Column(name = "lesson_delivery", nullable = false)
    private Instant lessonDelivery;
}