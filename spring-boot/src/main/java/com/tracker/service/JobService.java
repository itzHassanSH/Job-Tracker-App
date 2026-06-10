package com.tracker.service;

import com.tracker.dto.CreateJobRequest;
import com.tracker.dto.JobEventResponse;
import com.tracker.dto.JobResponse;
import com.tracker.entity.EventType;
import com.tracker.entity.Job;
import com.tracker.entity.JobEvent;
import com.tracker.entity.Status;
import com.tracker.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class JobService {

    private final JobRepository repo;

    public JobService (JobRepository repo) {
        this.repo = repo;
    }

    public JobResponse createJob (CreateJobRequest request) {
        Job job = new Job.Builder()
                .description(request.description())
                .company(request.company())
                .status(Status.APPLIED)
                .location(request.location())
                .contactPerson(request.contactPerson())
                .notes(request.notes())
                .build();
        JobEvent event = new JobEvent.Builder()
                .type(EventType.APPLICATION_SENT)
                .date(LocalDate.now())
                .build();
        job.addEvent(event);
        repo.save(job);


        ArrayList<JobEventResponse> eventResponses = new ArrayList<>();
        eventResponses.add(new JobEventResponse(event.getType(), event.getDate()));
        return new JobResponse(
                job.getDescription(),
                job.getCompany(),
                job.getLocation(),
                job.getStatus(),
                job.getContactPerson(),
                job.getNotes(),
                eventResponses
        );

    }
}
