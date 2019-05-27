package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.ReviewService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ReviewsDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_REVIEW_URL;

@Controller
@RequestMapping("/private")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/review")
    public String showCommentsPage(
            @RequestParam(defaultValue = "1", value = "currentPage") Integer page,
            Model model) {
        int valueOfPages = reviewService.getValueOfPages();
        model.addAttribute("numberPage", valueOfPages);
        List<ReviewDTO> reviewDTOS = reviewService.getReviewsForShowing(page);
        ReviewsDTO reviewsDTO = new ReviewsDTO();
        reviewsDTO.setReviewList(reviewDTOS);
        model.addAttribute("reviews", reviewsDTO);
        return "review";
    }

    @PostMapping("review/showing")
    public String updateItem(
            ReviewsDTO reviewsDTO
    ) {
        reviewService.changeHiddenStatus(reviewsDTO.getReviewList());
        return REDIRECT_REVIEW_URL;
    }

    @PostMapping("/review/delete")
    public String changePassword(
            ReviewDTO reviewDTO
    ) {
        reviewService.deleteReview(reviewDTO.getId());
        return REDIRECT_REVIEW_URL;
    }
}
