package com.gmail.salahub.nikolay.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Role;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.RoleConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.RoleConverterImpl;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.RoleDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoleConverterTest {

    private RoleConverter roleConverter;

    @Before
    public void init() {
        roleConverter = new RoleConverterImpl();
    }

    @Test
    public void shouldReturnRoleWithSameId() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        Role role = roleConverter.fromDTO(roleDTO);
        assertEquals(roleDTO.getId(), role.getId());
    }

    @Test
    public void shouldReturnRoleWithSameName() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("test");
        Role role = roleConverter.fromDTO(roleDTO);
        assertEquals(roleDTO.getName(), role.getName());
    }

    @Test
    public void shouldReturnRoleDTOWithSameId() {
        Role role = new Role();
        role.setId(1L);
        RoleDTO roleDTO = roleConverter.toDTO(role);
        assertEquals(roleDTO.getId(), role.getId());
    }

    @Test
    public void shouldReturnRoleDTOWithSomeName() {
        Role role = new Role();
        role.setName("test");
        RoleDTO roleDTO = roleConverter.toDTO(role);
        assertEquals(role.getId(), roleDTO.getId());
    }
}
