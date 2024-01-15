package com.sistemapacto.server.enums;

public enum RoleProfile {
    ADMIN(0), RECRUITER(1), CANDIDATE(2);

    private Integer roleProfile;

    RoleProfile(Integer role) {
        this.roleProfile = role;
    }

    public Integer getRole() {
        return roleProfile;
    }
}
