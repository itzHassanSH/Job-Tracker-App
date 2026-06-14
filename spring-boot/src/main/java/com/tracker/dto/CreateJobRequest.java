package com.tracker.dto;


/**
 * Contains everything the Frontend sends to backend
 * Frontend sends DFO - Jackson deserializes it, Controller then sends it to service
 * @param description
 * @param company
 * @param location
 * @param contactPerson
 * @param title
 */
public record CreateJobRequest(
        String description,
        String company,
        String location,
        String contactPerson,
        String title
) {
}
