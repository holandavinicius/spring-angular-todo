package com.maia.vinicius.dto.request;

import com.maia.vinicius.enums.Status;

public class StatusUpdateDTO {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
