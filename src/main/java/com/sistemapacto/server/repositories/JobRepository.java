package com.sistemapacto.server.repositories;

import com.sistemapacto.server.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
