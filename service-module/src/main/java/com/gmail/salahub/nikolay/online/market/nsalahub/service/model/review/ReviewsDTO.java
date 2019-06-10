package com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;

import java.util.ArrayList;
import java.util.List;

public class ReviewsDTO {

    private List<ReviewDTO> reviewList;

    public ReviewsDTO() {
        this.reviewList = new ArrayList<>();
    }

    public List<ReviewDTO> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewDTO> reviewList) {
        this.reviewList = reviewList;
    }
}
