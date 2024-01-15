package com.sistemapacto.server.repositories;

import com.sistemapacto.server.entities.LoginUserLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserLogsRepository extends JpaRepository<LoginUserLogs, Long> {

}
