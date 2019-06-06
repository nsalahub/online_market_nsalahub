package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.ReviewService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewsDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.UserPrincipal;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.ReviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_ARTICLES_CUSTOMER_USER_URL;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_REVIEW_URL;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final ReviewValidator reviewValidator;

    @Autowired
    public ReviewController(ReviewService reviewService,
                            UserService userService,
                            ReviewValidator reviewValidator) {
        this.reviewValidator = reviewValidator;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping("/private/review")
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

    @PostMapping("/private/review/showing")
    public String updateHiddenStatus(
            ReviewsDTO reviewsDTO
    ) {
        reviewService.changeHiddenStatus(reviewsDTO.getReviewList());
        return REDIRECT_REVIEW_URL;
    }

    @PostMapping("/private/review/delete")
    public String deleteReview(
            ReviewDTO reviewDTO
    ) {
        reviewService.deleteReview(reviewDTO.getId());
        return REDIRECT_REVIEW_URL;
    }

    @GetMapping("/public/review/new")
    public String showCreatingReviewPage(Model model) {
        ReviewDTO reviewDTO = new ReviewDTO();
        model.addAttribute("review", reviewDTO);
        return "createreview";
    }

    @PostMapping("/public/review/new")
    public String createNewReview(
            @ModelAttribute(value = "review")
                    ReviewDTO reviewDTO,
            BindingResult bindingResult,
            Model model) {
        reviewValidator.validate(reviewDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("review", reviewDTO);
            return "createreview";
        } else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDTO userDTO = userService.getById(((UserPrincipal) userDetails).getIdFromUserPrincipal());
            reviewDTO.setUserDTO(userDTO);
            reviewService.create(reviewDTO);
            return REDIRECT_ARTICLES_CUSTOMER_USER_URL;
        }
    }
}
