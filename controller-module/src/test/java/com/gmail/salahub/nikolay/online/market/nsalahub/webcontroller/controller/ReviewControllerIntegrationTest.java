package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;

import com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller.testmodel.TestModel;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ReviewDTO reviewDTO = TestModel.getTestReviewDTO();


}
