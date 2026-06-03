package com.tracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class JobEvent {
    @Id
    @GeneratedValue
    long id;

    // Many JobEvents belong to a single Job
    // In the job_event table, the column that stores the relationship is job_id
    // Whenever job is referenced in another entity, its identifier is id
    @ManyToOne
    @JoinColumn(name = "job_id")
    Job job;

    LocalDate date;

    @Enumerated(EnumType.STRING)
    EventType type;

}
