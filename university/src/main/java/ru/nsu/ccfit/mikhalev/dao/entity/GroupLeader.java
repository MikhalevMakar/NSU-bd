package ru.nsu.ccfit.mikhalev.dao.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"student", "group"})
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "group_leader", schema = "university")
@Entity
public class GroupLeader {

    @Id
    @OneToOne
    @JoinColumn(name = "group_id", referencedColumnName = "number")
    private Group group;

    @OneToOne
    @JoinColumn(name = "leader_student_id", referencedColumnName = "id")
    private Student student;
}