package com.tracker.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(Long id) {
        super("Job with id: " + id +" not found");
    }
}
