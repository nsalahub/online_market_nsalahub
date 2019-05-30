package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.ReviewService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;

import com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller.testmodel.TestModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ReviewService reviewService;

    private ReviewDTO reviewDTO = TestModel.getTestReviewDTO();
    private List<ReviewDTO> reviewDTOS = asList(reviewDTO, reviewDTO);
    private Integer numberOfPage = 0;

    @Before
    public void init() {
        ReviewController reviewController = new ReviewController(reviewService);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR"})
    public void shouldGetReviewPageWithSomeReviews() throws Exception {
        when(reviewService.getReviewsForShowing(1)).thenReturn(reviewDTOS);
        when(reviewService.getValueOfPages()).thenReturn(numberOfPage);
        this.mockMvc.perform(get("/private/review.html"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("numberPage", numberOfPage))
                .andExpect(model().attributeExists("reviews"))
                .andExpect(forwardedUrl("review"));
    }
}
