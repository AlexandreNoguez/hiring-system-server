package com.sistemapacto.server.entities;

import com.sistemapacto.server.entities.pk.UserJob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "jobs")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", updatable = false, nullable = false)
    private Long jobId;

    @Column(name = "job_title")
    private String title;

    @Column(name = "job_description")
    private String description;

    @Column(name = "job_requirements")
    private String requirements;

    @Column(name = "job_status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "recruiter_email")
    private String userRecruiterEmail;

    @OneToMany(mappedBy = "jobEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserJob> userJobs;



}
