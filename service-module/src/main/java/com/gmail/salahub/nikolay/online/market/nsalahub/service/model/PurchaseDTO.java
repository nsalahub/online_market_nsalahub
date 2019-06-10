package com.gmail.salahub.nikolay.online.market.nsalahub.service.model;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;

import javax.validation.constraints.NotNull;

public class PurchaseDTO {

    @NotNull
    private String id;
    @NotNull
    private String quantity;
    private UserDTO userDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
