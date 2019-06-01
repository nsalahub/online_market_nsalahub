package com.gmail.salahub.nikolay.online.market.nsalahub.service.model.order;

import javax.validation.constraints.NotNull;

public class UpdateOrderDTO {
    @NotNull
    private String id;
    @NotNull
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}