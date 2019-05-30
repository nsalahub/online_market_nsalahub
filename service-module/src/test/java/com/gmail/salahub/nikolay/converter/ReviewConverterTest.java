package com.gmail.salahub.nikolay.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.review.Review;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.User;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ReviewConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.ReviewConverterImpl;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import com.gmail.salahub.nikolay.testmodel.TestModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReviewConverterTest {

    @Mock
    private UserConverter userConverter;

    private ReviewConverter reviewConverter;

    private UserDTO userDTO = TestModel.getTestUserDTO();
    private User user = TestModel.getTestUser();

    @Before
    public void init() {
        reviewConverter = new ReviewConverterImpl(userConverter);
        when(userConverter.fromDTO(userDTO)).thenReturn(user);
        when(userConverter.toDTO(user)).thenReturn(userDTO);
    }

    @Test
    public void shouldReturnReviewIdFromReviewDTO() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(1L);
        Review review = reviewConverter.fromDTO(reviewDTO);
        assertEquals(reviewDTO.getId(), review.getId());
    }

    @Test
    public void shouldReturnReviewReviewFromReviewDTO() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReview("review");
        Review review = reviewConverter.fromDTO(reviewDTO);
        assertEquals(reviewDTO.getReview(), reviewDTO.getReview());
    }

    @Test
    public void shouldReturnReviewShowingFromReviewDTO() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setShowing(true);
        Review review = reviewConverter.fromDTO(reviewDTO);
        assertEquals(review.isShowing(), reviewDTO.isShowing());
    }

    @Test
    public void shouldReturnDeletedFromReviewDTO() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setDeleted(true);
        Review review = reviewConverter.fromDTO(reviewDTO);
        assertEquals(review.isDeleted(), reviewDTO.isDeleted());
    }

    @Test
    public void shouldReturnDateFromReviewDTO() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setCreatingDate(new Date(2002 - 02 - 19));
        Review review = reviewConverter.fromDTO(reviewDTO);
        assertEquals(review.getCreatingDate(), reviewDTO.getCreatingDate());
    }

    @Test
    public void shouldReturnUserFromReviewDTO() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setUserDTO(userDTO);
        Review review = reviewConverter.fromDTO(reviewDTO);
        assertEquals(review.getUser().getEmail(), reviewDTO.getUserDTO().getEmail());
    }

    @Test
    public void shouldReturnIdFromReview() {
        Review review = new Review();
        review.setId(1L);
        ReviewDTO reviewDTO = reviewConverter.toDTO(review);
        assertEquals(review.getId(), reviewDTO.getId());
    }

    @Test
    public void shouldReturnReviewFromReview() {
        Review review = new Review();
        review.setReview("review");
        ReviewDTO reviewDTO = reviewConverter.toDTO(review);
        assertEquals(review.getReview(), reviewDTO.getReview());
    }

    @Test
    public void shouldReturnShowingFromReview() {
        Review review = new Review();
        review.setShowing(true);
        ReviewDTO reviewDTO = reviewConverter.toDTO(review);
        assertEquals(review.isShowing(), reviewDTO.isShowing());
    }

    @Test
    public void shouldReturnDeletedFromReview() {
        Review review = new Review();
        review.setDeleted(true);
        ReviewDTO reviewDTO = reviewConverter.toDTO(review);
        assertEquals(review.isDeleted(), reviewDTO.isDeleted());
    }

    @Test
    public void shouldReturnDateFromReview() {
        Review review = new Review();
        review.setCreatingDate(new Date(2002 - 02 - 19));
        ReviewDTO reviewDTO = reviewConverter.toDTO(review);
        assertEquals(review.getCreatingDate(), reviewDTO.getCreatingDate());
    }

    @Test
    public void shouldReturnUserFromReview() {
        Review review = new Review();
        review.setUser(user);
        ReviewDTO reviewDTO = reviewConverter.toDTO(review);
        assertEquals(reviewDTO.getUserDTO().getEmail(), review.getUser().getEmail());
    }
}
