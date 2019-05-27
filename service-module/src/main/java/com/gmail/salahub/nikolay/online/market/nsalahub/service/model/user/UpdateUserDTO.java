package com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user;

import javax.validation.constraints.NotNull;

public class UpdateUserDTO {
    @NotNull
    private String email;
    @NotNull
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
