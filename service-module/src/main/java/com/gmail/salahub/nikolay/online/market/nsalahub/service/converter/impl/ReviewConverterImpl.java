package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.review.Review;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ReviewConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("reviewConverter")
public class ReviewConverterImpl implements ReviewConverter {

    private final UserConverter userConverter;

    @Autowired
    public ReviewConverterImpl(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public Review fromDTO(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setReview(reviewDTO.getReview());
        review.setShowing(reviewDTO.isShowing());
        review.setDeleted(reviewDTO.isDeleted());
        review.setCreatingDate(reviewDTO.getCreatingDate());
        review.setUser(userConverter.fromDTO(reviewDTO.getUserDTO()));
        return review;
    }

    @Override
    public ReviewDTO toDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setReview(review.getReview());
        reviewDTO.setShowing(review.isShowing());
        reviewDTO.setDeleted(review.isDeleted());
        reviewDTO.setCreatingDate(review.getCreatingDate());
        reviewDTO.setUserDTO(userConverter.toDTO(review.getUser()));
        return reviewDTO;
    }
}
