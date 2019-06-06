package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_REVIEW_REVIEW_MESSAGE;

@Component
public class ReviewValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ReviewDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "review", "", VALIDATION_REVIEW_REVIEW_MESSAGE);
    }
}
