package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.review.Review;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;

public interface ReviewConverter {
    Review fromDTO(ReviewDTO reviewDTO);

    ReviewDTO toDTO(Review review);
}
