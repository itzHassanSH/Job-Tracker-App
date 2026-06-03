package com.tracker.entity;

// Jakarta Persistence is the standard specification for working with databases in Java
// Spring Data JPA Dependency required for this package - Spring Boot + JPA (or Hibernate)

import jakarta.persistence.*;
import java.util.List;

/**
 * Represents a job application tracked by the user.
 * <p>
 * Each Job can have multiple associated events such as
 * application submission, follow-ups, interviews, and offers.
 */
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String description;
    private String location;
    private Status status;
    private String contactPerson;
    private String notes;

    // One Job can have many Job events
    /*
    mappedBy = "job" means:
    Don't create a new foreign key column here - the relationship is already managed on the other side by the
    field name "job" in JobEvent
    */
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    List<JobEvent> events;


    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    private Job (Builder builder) {
        this.description = builder.description;
        this.location = builder.location;
        this.status = builder.status;
        this.contactPerson = builder.contactPerson;
        this.notes = builder.notes;
        this.company = builder.company;
    }

    // Hibernate (JPA) requires an argument-empty constructor so that it can map objects and database rows
    // Essentially serializes/parses data for storage in the database e.g.
    // Job job = (Job) Class.forName("Job").newInstance();
    //    conceptually: Job job = unsafeCreateObjectWithoutCallingYourConstructor();
    public Job () {}

    public static class Builder {

        private String company;
        private String description;
        private String location;
        private Status status;
        private String contactPerson;
        private String notes;
        @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
        List<JobEvent> events;

        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder location(String location) {
            this.location = location;
            return this;
        }
        public Builder status(Status status) {
            this.status = status;
            return this;
        }
        public Builder contactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
            return this;
        }
        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }
        public Builder events(List<JobEvent> events) {
            this.events = events;
            return this;
        }
        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Job build() {
            return new Job(this);
        }
    }
}


