package com.sistemapacto.server.services;

import com.sistemapacto.server.dto.skill.SkillDTO;
import com.sistemapacto.server.dto.user.LoggedUserDTO;
import com.sistemapacto.server.dto.user.UserCreateDTO;
import com.sistemapacto.server.dto.user.UserDTO;
import com.sistemapacto.server.dto.userrole.UserRoleDTO;
import com.sistemapacto.server.entities.RoleEntity;
import com.sistemapacto.server.entities.SkillEntity;
import com.sistemapacto.server.entities.UserEntity;
import com.sistemapacto.server.entities.pk.SkillUser;
import com.sistemapacto.server.entities.pk.UserRole;
import com.sistemapacto.server.exceptions.BusinessException;
import com.sistemapacto.server.repositories.SkillRepository;
import com.sistemapacto.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private ZoneId ZONE_BRAZIL = ZoneId.of("America/Sao_Paulo");

    /*
    * Corrigir create new user letras maiusculas e minusculas login e email
    * */
    public UserDTO createNewUser(UserCreateDTO userCreateDTO) throws BusinessException, IOException {

        Optional<UserEntity> user = userRepository.findByUserLogin(userCreateDTO.getUserLogin());

        if (user.isPresent()) {
            throw new BusinessException("Login do usuário já cadastrado");
        }

        Optional<UserEntity> userEmail = userRepository.findByUserEmail(userCreateDTO.getUserEmail());
        if (userEmail.isPresent() && userCreateDTO.getUserEmail().equalsIgnoreCase(userEmail.get().getUserEmail())) {
            throw new BusinessException("E-mail informado já cadastrado.");
        }

        if (!userCreateDTO.getUserPassword().equals(userCreateDTO.getMatchUserPassword())) {
            throw new BusinessException("Senhas devem ser identicas!");
        }

        if (!verifyPassword(userCreateDTO.getUserPassword())) {
            throw new BusinessException("Senha não atingiu os requisitos mínimos de segurança");
        }

        String encryptPassword = passwordEncoder.encode(userCreateDTO.getUserPassword());


        UserEntity userEntity = userConvertEntity(userCreateDTO);

        userEntity.setCreatedAt(ZonedDateTime.now(ZONE_BRAZIL).toLocalDateTime());

        Set<UserRole> userRoles = new HashSet<>();


        userEntity.setUserPassword(encryptPassword);

        userCreateDTO.getRoles().stream()
                .map(roleEntity -> {
                    RoleEntity role = null;
                    try {
                        role = roleService.findByRoleName(roleEntity.getRoleName());
                    } catch (BusinessException e) {
                        throw new RuntimeException(e);
                    }
                    UserRole userRole = new UserRole();
                    userRole.setRoleEntity(role);
                    userRole.setUserEntity(userEntity);
                    userRoles.add(userRole);

                    return role;
                })
                .collect(Collectors.toSet());

        var skillsDb = skillRepository.findAll();

        Set<SkillUser> skillsEntity = userCreateDTO.getSkills().stream()
                .map(skillDTO -> {
                    SkillEntity skillFromDb = skillsDb.stream()
                            .filter(skill -> skill.getSkillId().equals(skillDTO.getSkillId()))
                            .findFirst()
                            .orElse(null);

                    if (skillFromDb != null) {
                        SkillUser skillEntity = new SkillUser();
                        skillEntity.setSkillEntity(skillFromDb);
                        skillEntity.setUserEntity(userEntity);
                        skillEntity.setExperience(skillDTO.getExperience());
                        skillEntity.setLastUsed(skillDTO.getLastUsed());
                        return skillEntity;
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());



        userEntity.setSkills(skillsEntity);
        userEntity.setUserRole(userRoles);

        userRepository.save(userEntity);

        return userConvertEntityToDTO(userEntity);
    }

    private UserDTO userConvertEntityToDTO(UserEntity userEntity) {
        Set<UserRoleDTO> roles = userEntity.getUserRole().stream()
                .map(this::convertUserRoleDTO)
                .collect(Collectors.toSet());

        Set<SkillDTO> skills = userEntity.getSkills().stream()
                .map(this::convertSkillDTO)
                .collect(Collectors.toSet());

        return new UserDTO(userEntity.getUserId(),
                userEntity.getUserLogin(),
                userEntity.getUserEmail(),
                roles,
                skills
                );
    }

    public Boolean verifyPassword(String senha) {
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }

    private UserEntity userConvertEntity(UserCreateDTO userCreateDTO) throws IOException {
        return modelMapper.map(userCreateDTO,UserEntity.class);
    }

    private UserDTO convertUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserRoleDTO convertUserRoleDTO (UserRole userRole) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRoleId(userRole.getRoleEntity().getRoleId());
        userRoleDTO.setRoleName(userRole.getRoleEntity().getRoleName());

        return userRoleDTO;
    }

    public SkillDTO convertSkillDTO(SkillUser skillUser) {
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setSkillId(skillUser.getSkillEntity().getSkillId());

        skillDTO.setTitle(skillUser.getSkillEntity().getTitle());

        return skillDTO;
    }

    public Optional<UserEntity> findByUserLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            return Optional.empty();
        }
        return userRepository.findByUserLogin(login);
    }

    public Long getIdLoggerdUser() throws BusinessException {
        return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    public LoggedUserDTO getLoggedUser() throws BusinessException {
        return new LoggedUserDTO(getUserById(getIdLoggerdUser()));
    }

    public UserEntity getUserById(Long userId) throws BusinessException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("Usuario não encontrado."));

    }


}
