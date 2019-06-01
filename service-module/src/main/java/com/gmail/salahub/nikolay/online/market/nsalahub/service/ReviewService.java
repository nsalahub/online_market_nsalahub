package com.gmail.salahub.nikolay.online.market.nsalahub.service;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getReviewsForShowing(Integer pageNumber);

    int getValueOfPages();

    void deleteReview(Long id);

    void changeHiddenStatus(List<ReviewDTO> reviewDTOS);

    void create(ReviewDTO reviewDTO);
}
