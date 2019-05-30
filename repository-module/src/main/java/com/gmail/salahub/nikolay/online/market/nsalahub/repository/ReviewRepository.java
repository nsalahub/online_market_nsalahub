package com.gmail.salahub.nikolay.online.market.nsalahub.repository;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.review.Review;

import java.util.List;

public interface ReviewRepository extends GenericRepository<Long, Review> {
    void updateButchStatusById(List<String> showingString, List<String> stringsIds);

    void deleteById(Long id);
}
