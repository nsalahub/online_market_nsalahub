package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.RoleDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_USER_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR"})
    public void shouldGetUsersPageWithSomeUsers() throws Exception {
        this.mockMvc.perform(get("/private/user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"ADMINISTRATOR"})
    public void shouldSaveSomeUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("ADMINISTRATOR");
        userDTO.setRoleDTO(roleDTO);
        userDTO.setSurname("surname");
        userDTO.setName("name");
        userDTO.setPatronymic("patr");
        userDTO.setEmail("email234234234234234@email");
        this.mockMvc.perform(post("/private/user/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("user",userDTO))
                .andExpect(status().isOk())
                .andExpect(redirectedUrl(REDIRECT_USER_URL));
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR"})
    public void shouldDeleteSameUser() throws Exception {
        String[] emails = {"mail", "mail2"};
        this.mockMvc.perform(post("/private/user/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().isOk())
                .andExpect(redirectedUrl(REDIRECT_USER_URL));
    }
}
