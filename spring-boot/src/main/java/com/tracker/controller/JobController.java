package com.tracker.controller;

import com.tracker.dto.CreateJobRequest;
import com.tracker.dto.JobResponse;
import com.tracker.service.JobService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController (JobService service) {
        this.jobService = service;
    }

    @PostMapping
    public ResponseEntity<JobResponse> createJob(@RequestBody CreateJobRequest request) {
        JobResponse response = jobService.createJob(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
