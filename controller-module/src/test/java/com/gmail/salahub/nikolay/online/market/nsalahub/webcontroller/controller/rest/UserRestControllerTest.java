package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;

import com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller.testmodel.TestModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class UserRestControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    private UserDTO userDTO = TestModel.getTestUserForRest();

    @Test
    public void shouldCreateUser() throws Exception {
        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .content(asJsonString(userDTO)))
                .andExpect(status().isCreated());
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
