package com.sistemapacto.server.dto.user;

import com.sistemapacto.server.dto.skill.SkillDTO;
import com.sistemapacto.server.dto.userrole.UserRoleDTO;
import com.sistemapacto.server.entities.UserEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Set;

@Data
public class LoggedUserDTO {
    private Long userId;
    private String userLogin;
    private String userEmail;
    private Set<UserRoleDTO> userRole;
    private Set<SkillDTO> skills;
    public LoggedUserDTO(UserEntity userEntity) {
        BeanUtils.copyProperties(userEntity, this);
    }
}
