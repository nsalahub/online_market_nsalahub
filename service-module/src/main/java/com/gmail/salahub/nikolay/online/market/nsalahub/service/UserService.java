package com.gmail.salahub.nikolay.online.market.nsalahub.service;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UpdateUserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getByUsername(String username);

    List<UserDTO> getByPageNumber(int pageNumber);

    int getNumberOfPages();

    void create(UserDTO userDTO);

    void update(UpdateUserDTO updateUserDTO);

    void updatePassword(UserDTO userDTO);

    void deleteByListIds(List<Long> ids);

    void updateProfile(UserDTO userDTO);

    UserDTO getById(Long id);
}
