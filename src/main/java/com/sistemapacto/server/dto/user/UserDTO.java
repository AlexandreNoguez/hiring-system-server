package com.sistemapacto.server.dto.user;

import com.sistemapacto.server.dto.skill.SkillDTO;
import com.sistemapacto.server.dto.userrole.UserRoleDTO;
import com.sistemapacto.server.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long userId;
    private String userLogin;
    private String userName;
    private String userEmail;
    private Set<UserRoleDTO> userRole;
    private Set<SkillDTO> skills;
    public UserDTO(UserEntity userEntity) {
        BeanUtils.copyProperties(userEntity, this);
    }

}
