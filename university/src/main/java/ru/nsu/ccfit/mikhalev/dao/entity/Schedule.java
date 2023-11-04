package ru.nsu.ccfit.mikhalev.dao.entity;

import jakarta.persistence.*;

@Table(name="schedule")
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;


}
