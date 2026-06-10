package com.tracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
public class JobEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    // Many JobEvents belong to a single Job
    // In the job_event table, the column that stores the relationship is job_id
    // Whenever job is referenced in another entity, its identifier is id
    @Setter
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    LocalDate date;

    // Store the name of the enum constant as a string in the database
    @Enumerated(EnumType.STRING)
    EventType type;

    public JobEvent() {}

    private JobEvent(Builder builder) {
        this.date = builder.date;
        this.type = builder.type;
        // job is set inside job.addEvent
    }

    public static class Builder {
        private LocalDate date;
        private EventType type;

        public JobEvent build () {
            return new JobEvent(this);
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }
        public Builder type(EventType type) {
            this.type = type;
            return this;
        }

    }

}
