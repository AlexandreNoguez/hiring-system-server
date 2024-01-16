package com.sistemapacto.server.repositories;

import com.sistemapacto.server.entities.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

    @Query( "SELECT distinct jb " +
            " FROM jobs jb " +
            " WHERE (:jobId is null or jb.jobId = :jobId)" +
            " AND (:title is null or UPPER(jb.title) LIKE UPPER(concat('%', :title, '%'))) " +
            " AND (:description is null or UPPER(jb.description) LIKE UPPER(concat('%',:description, '%'))) " +
            " AND (:requirements is null or UPPER(jb.requirements) LIKE UPPER(concat('%',:requirements, '%'))) " +
            " AND (:status is null or UPPER(jb.status) LIKE UPPER(concat('%', :status, '%'))) " +
            "")
    Page<JobEntity> getAllJobs(
            Long jobId,
            String title,
            String description,
            String requirements,
            String status,
            PageRequest pageRequest
    );



}
