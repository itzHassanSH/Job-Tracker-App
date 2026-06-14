package com.tracker.entity;

// Jakarta Persistence is the standard specification for working with databases in Java
// Spring Data JPA Dependency required for this package - Spring Boot + JPA (or Hibernate)

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a job application tracked by the user.
 * <p>
 * Each Job can have multiple associated events such as
 * application submission, follow-ups, interviews, and offers.
 */
@Entity
@Getter
public class Job {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String company;
    private  String description;
    private  String location;

    @Enumerated(EnumType.STRING)
    private  Status status;

    private  String contactPerson;
    private  String title;

    // One Job can have many Job events
    /*
    mappedBy = "job" means:
    Don't create a new foreign key column here - the relationship is already managed on the other side by the
    field name "job" in JobEvent
    */
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<JobEvent> events;

    public void addEvent(JobEvent event) {
        this.events.add(event);
        event.setJob(this);
    }
    public void updateContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public void changeStatus(Status newStatus) {
        this.status = newStatus;
    }

    private Job (Builder builder) {
        this.description = builder.description;
        this.location = builder.location;
        this.status = builder.status;
        this.company = builder.company;
        this.contactPerson = builder.contactPerson;
        this.title = builder.title;
        this.events = new ArrayList<>();
    }

    /**
    Hibernate (JPA) requires an argument-empty constructor so that it can map objects and database rows
     <p>
    Essentially serializes/parses data for storage in the database e.g:
     <p>
     <code>Job job = (Job) Class.forName("Job").newInstance();</code>
     <p>
     conceptually:
     <code>Job job = unsafeCreateObjectWithoutCallingYourConstructor();</code>
     */
    public Job () {}

    public static class Builder {

        private String company;
        private String description;
        private String location;
        private String contactPerson;
        private String title;
        private Status status;

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
        public Builder company(String company) {
            this.company = company;
            return this;
        }
        public Builder contactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
            return this;
        }
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Job build() {
            return new Job(this);
        }
    }
}


