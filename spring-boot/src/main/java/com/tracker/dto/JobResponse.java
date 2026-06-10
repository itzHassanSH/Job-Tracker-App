package com.tracker.dto;


import com.tracker.entity.Status;
import java.util.List;

/**
 * Contains everything the Frontend actually needs -  used later for secure transmission of data
 * Service returns DTO - Controller receives it and Spring serializes it (Jackson)
 * @param description
 * @param company
 * @param location
 * @param status
 * @param contactPerson
 * @param notes
 * @param events
 */
public record JobResponse(
    String description,
    String company,
    String location,
    Status status,
    String contactPerson,
    String notes,
    List<JobEventResponse> events
) {}
