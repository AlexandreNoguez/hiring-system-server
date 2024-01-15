package com.sistemapacto.server.dto.skilluser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkillUserDTO {
    private Long skillUserId;
    private String experience;
    private LocalDateTime lastUsed;
//    private SkillEntity skillEntity;
//    private UserEntity userEntity;

}
