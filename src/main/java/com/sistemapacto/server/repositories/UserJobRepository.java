package com.sistemapacto.server.repositories;

import com.sistemapacto.server.entities.pk.UserJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJobRepository extends JpaRepository<UserJob, Long> {

}
