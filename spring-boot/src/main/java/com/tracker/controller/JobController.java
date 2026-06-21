package com.tracker.controller;

import com.tracker.dto.CreateJobRequest;
import com.tracker.dto.JobResponse;
import com.tracker.dto.UpdateJobRequest;
import com.tracker.service.JobService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<JobResponse> getJobs() {
        return jobService.findAllJobs();
    }

    //   "/jobs" is already defined by RequestMapping annotation at the top!
    @DeleteMapping("/{id}")
    public void deleteJobs(@PathVariable Long id) {
        jobService.deleteJob(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable Long id, @RequestBody UpdateJobRequest request) {
        return new ResponseEntity<>(jobService.updateJob(id, request), HttpStatus.OK);
    }
}
