package com.tracker.dto;

import com.tracker.entity.EventType;
import java.time.LocalDate;

public record JobEventResponse(
        EventType type,
        LocalDate date
) {
}
