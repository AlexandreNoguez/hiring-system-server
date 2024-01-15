package com.sistemapacto.server.enums;

public enum ApplicationStatus {
    SENT(0), INTERVIEW(1), ENDED(2);

    private Integer applicationStatus;

    ApplicationStatus(Integer applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Integer getStatus() {
        return applicationStatus;
    }
}
