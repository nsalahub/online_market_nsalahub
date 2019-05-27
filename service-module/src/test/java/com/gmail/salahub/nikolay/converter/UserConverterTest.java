package com.gmail.salahub.nikolay.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Role;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.User;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.RoleConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.user.UserConverterImpl;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.RoleDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.testmodel.TestModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {
    @Mock
    private RoleConverter roleConverter;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserConverter userConverter;

    private RoleDTO roleDTO = TestModel.getTestRoleDTO();
    private Role role = TestModel.getTestRole();

    @Before
    public void init() {
        userConverter = new UserConverterImpl(roleConverter, passwordEncoder);
        when(roleConverter.fromDTO(roleDTO)).thenReturn(role);
        when(roleConverter.toDTO(role)).thenReturn(roleDTO);
    }

    @Test
    public void shouldReturnUserIdFromUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        User user = userConverter.fromDTO(userDTO);
        assertEquals(user.getId(), userDTO.getId());
    }

    @Test
    public void shouldReturnUserNameFromUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("test");
        User user = userConverter.fromDTO(userDTO);
        assertEquals(user.getName(), userDTO.getName());
    }

    @Test
    public void shouldReturnUserSurnameFromUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setSurname("test");
        User user = userConverter.fromDTO(userDTO);
        assertEquals(user.getSurname(), userDTO.getSurname());
    }

    @Test
    public void shouldReturnUserPatronymicFromUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPatronymic("test");
        User user = userConverter.fromDTO(userDTO);
        assertEquals(userDTO.getPatronymic(), user.getPatronymic());
    }

    @Test
    public void shouldReturnUserEmailFromUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email");
        User user = userConverter.fromDTO(userDTO);
        assertEquals(userDTO.getEmail(), user.getEmail());
    }

    @Test
    public void shouldReturnUserPasswordFromUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("123");
        User user = userConverter.fromDTO(userDTO);
        assertEquals(userDTO.getPassword(), user.getPassword());
    }

    @Test
    public void shouldReturnUserDeletedFromUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setDeleted(false);
        User user = userConverter.fromDTO(userDTO);
        assertEquals(userDTO.isDeleted(), user.isDeleted());

    }

    @Test
    public void shouldReturnUserWithSameRoleFromUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setRoleDTO(roleDTO);
        Role role = roleConverter.fromDTO(roleDTO);
        assertEquals(role.getName(), roleDTO.getName());
        User user = userConverter.fromDTO(userDTO);
        assertEquals(user.getRole().getName(), userDTO.getRoleDTO().getName());
    }

    @Test
    public void shouldReturnUserDTOWithSameIdFromUser() {
        User user = new User();
        user.setId(1L);
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getId(), userDTO.getId());
    }

    @Test
    public void shouldReturnUserDTOWithSameNameFromUser() {
        User user = new User();
        user.setName("name");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getName(), userDTO.getName());
    }

    @Test
    public void shouldReturnUserDTOWithSameSurnameFromUser() {
        User user = new User();
        user.setSurname("surname");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getSurname(), userDTO.getSurname());
    }

    @Test
    public void shouldReturnUserDTOWithPatronymicFromUser() {
        User user = new User();
        user.setPatronymic("patronymic");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getPatronymic(), userDTO.getPatronymic());
    }

    @Test
    public void shouldReturnUserDTOWithEmailFromUser() {
        User user = new User();
        user.setEmail("email");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getEmail(), userDTO.getEmail());
    }

    @Test
    public void shouldReturnUserDTOWithPasswordFromUser() {
        User user = new User();
        user.setPassword("password");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getPassword(), userDTO.getPassword());
    }

    @Test
    public void shouldReturnUserDTOWithDeletedFromUser() {
        User user = new User();
        user.setDeleted(false);
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(userDTO.isDeleted(), userDTO.isDeleted());
    }

    @Test
    public void shouldReturnUserDTOWithRoleFromUser() {
        User user = new User();
        user.setRole(role);
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(userDTO.getRoleDTO().getName(), user.getRole().getName());
    }
}
