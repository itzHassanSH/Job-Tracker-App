package com.tracker.dto;


import com.tracker.entity.JobEvent;
import com.tracker.entity.Status;

/**
 * Request object used to update an existing Job object
 * Contains the fields to be changed - ones to remain same are null
 * @param description
 * @param company
 * @param location
 * @param contactPerson
 * @param title
 * @param status
 * @param jobEvent
 */
public record UpdateJobRequest(
        String description,
        String company,
        String location,
        String contactPerson,
        String title,
        Status status,
        JobEvent jobEvent
) {
}
