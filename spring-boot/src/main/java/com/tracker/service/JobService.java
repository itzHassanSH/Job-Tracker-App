package com.tracker.service;

import com.tracker.dto.CreateJobRequest;
import com.tracker.dto.JobEventResponse;
import com.tracker.dto.JobResponse;
import com.tracker.entity.EventType;
import com.tracker.entity.Job;
import com.tracker.entity.JobEvent;
import com.tracker.entity.Status;
import com.tracker.repository.JobEventRepository;
import com.tracker.repository.JobRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobEventRepository jobEventRepository;

    public JobService (JobRepository jobRepository, JobEventRepository jobEventRepository) {
        // Repositories = doors into the database - not a database itself!
        this.jobRepository = jobRepository;
        this.jobEventRepository = jobEventRepository;
    }

    @NonNull
    public JobResponse createJob (CreateJobRequest request) {
        Job job = new Job.Builder()
                .description(request.description())
                .company(request.company())
                .status(Status.APPLIED)
                .location(request.location())
                .contactPerson(request.contactPerson())
                .title(StringUtils.capitalize(request.title().trim()))
                .build();
        JobEvent event = new JobEvent.Builder()
                .type(EventType.APPLICATION_SENT)
                .date(LocalDate.now())
                .build();
        job.addEvent(event);
        jobRepository.save(job);



        ArrayList<JobEventResponse> eventResponses = new ArrayList<>();
        eventResponses.add(new JobEventResponse(event.getType(), event.getDate()));
        return new JobResponse(
                job.getDescription(),
                job.getId(),
                job.getCompany(),
                job.getLocation(),
                job.getStatus(),
                job.getContactPerson(),
                job.getTitle(),
                eventResponses
        );

    }

    @NonNull
    public List<JobResponse> findAllJobs() {
        return jobRepository.findAll().stream()
                .map(job -> new JobResponse(
                        job.getDescription(),
                        job.getId(),
                        job.getCompany(),
                        job.getLocation(),
                        job.getStatus(),
                        job.getContactPerson(),
                        job.getTitle(),
                        job.getEvents().stream()
                                .map(jobEvent -> new JobEventResponse(
                                        jobEvent.getType(),
                                        jobEvent.getDate()
                                ))
                                .toList()
                )).toList();
    }
}
