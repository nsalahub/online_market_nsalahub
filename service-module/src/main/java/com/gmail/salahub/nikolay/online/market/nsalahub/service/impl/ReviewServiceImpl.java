package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.ReviewRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.review.Review;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.PageService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.ReviewService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ReviewConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.salahub.nikolay.online.market.nsalahub.repository.constant.RepositoryConstant.LIMIT_REVIEW_VALUE;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;
    private final PageService pageService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             ReviewConverter reviewConverter,
                             PageService pageService) {
        this.reviewConverter = reviewConverter;
        this.reviewRepository = reviewRepository;
        this.pageService = pageService;
    }

    @Override
    @Transactional
    public List<ReviewDTO> getReviewsForShowing(Integer pageNumber) {
        List<ReviewDTO> reviewDTOS;
        List<Review> reviews = reviewRepository.findAll(pageService
                .getLimitValue(LIMIT_REVIEW_VALUE, pageNumber), LIMIT_REVIEW_VALUE);
        reviewDTOS = reviews.stream()
                .map(reviewConverter::toDTO)
                .collect(Collectors.toList());
        return reviewDTOS;
    }

    @Override
    @Transactional
    public int getValueOfPages() {
        Integer valueOfUsers = reviewRepository.getCountOfEntities();
        Integer valueOfPages = pageService.getValueOfPages(valueOfUsers, LIMIT_REVIEW_VALUE);
        return valueOfPages;
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void changeHiddenStatus(List<ReviewDTO> reviewDTOS) {
        List<String> showingStrings = new ArrayList<>();
        reviewDTOS.stream().forEach(reviewDTO ->
                showingStrings.add(String.valueOf(reviewDTO.isShowing())));
        List<String> idStrings = new ArrayList<>();
        reviewDTOS.stream().forEach(reviewDTO ->
                idStrings.add(String.valueOf(reviewDTO.getId())));
        reviewRepository.updateButchStatusById(showingStrings, idStrings);
    }
}
