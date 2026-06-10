package com.tracker.repository;

import com.tracker.entity.Job;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<@NonNull Job, @NonNull Long> {

}
