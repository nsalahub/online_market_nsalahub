package com.gmail.salahub.nikolay.online.market.nsalahub.service.testmodel;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Role;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.UpdateUser;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.User;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.RoleDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UpdateUserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;

public class TestModel {

    public static Role getTestRole() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ADMINISTRATOR");
        return role;
    }

    public static RoleDTO getTestRoleDTO() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setName("ADMINISTRATOR");
        return roleDTO;
    }

    public static User getTestUser() {
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setSurname("surname");
        user.setPatronymic("patronymic");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        Role role = getTestRole();
        user.setRole(role);
        user.setDeleted(true);
        return user;
    }

    public static UserDTO getTestUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("name");
        userDTO.setSurname("surname");
        userDTO.setPatronymic("patronymic");
        userDTO.setEmail("email@gmail.com");
        userDTO.setPassword("password");
        RoleDTO role = getTestRoleDTO();
        userDTO.setRoleDTO(role);
        userDTO.setDeleted(true);
        return userDTO;
    }

    public static UpdateUser getTestUpdateUser(){
        UpdateUser updateUser = new UpdateUser();
        updateUser.setRole("ADMINISTRATOR");
        updateUser.setEmail("email@gmail.com");
        return updateUser;
    }

    public static UpdateUserDTO getTestUpdateUserDTO(){
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setEmail("email@gmail.com");
        updateUserDTO.setRole("ADMINISTRATOR");
        return updateUserDTO;
    }
}
