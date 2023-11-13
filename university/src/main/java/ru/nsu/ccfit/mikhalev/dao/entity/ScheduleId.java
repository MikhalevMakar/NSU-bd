package ru.nsu.ccfit.mikhalev.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class ScheduleId implements Serializable {

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "teacher_id")
    private Long teacherId;
}
