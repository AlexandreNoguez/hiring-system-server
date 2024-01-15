package com.sistemapacto.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "login_user_logs")
public class LoginUserLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id", updatable = false, nullable = false)
    private Long loginId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public LoginUserLogs(UserEntity userEntity, LocalDateTime updatedAt) {
        this.userEntity = userEntity;
        this.updatedAt = updatedAt;
    }
}
