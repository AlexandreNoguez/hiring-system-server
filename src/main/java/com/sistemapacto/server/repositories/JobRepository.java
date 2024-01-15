package com.sistemapacto.server.repositories;

import com.sistemapacto.server.entities.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

    @Query( "SELECT distinct func " +
            " FROM jobs func " +
            " inner join func.userJobs fcar " +
            " WHERE (:jobId is null or func.jobId = :jobId)" +
            " AND (:title is null or func.title LIKE concat('%', :title, '%')) " +
            " AND (:description is null or UPPER(func.description) LIKE UPPER(concat('%',:description, '%'))) " +
            " AND (:requirements is null or :requirements = func.requirements)" +
            " AND (:status is null or :status = fcar.applicationStatus ) " +
            "")
//    (cast(:userId as org.hibernate.type.UUIDCharType)
    Page<JobEntity> getAllJobs(
           Integer jobId,
           String title,
           String description,
           String requirements,
           String status,
           PageRequest pageRequest
    );



}
