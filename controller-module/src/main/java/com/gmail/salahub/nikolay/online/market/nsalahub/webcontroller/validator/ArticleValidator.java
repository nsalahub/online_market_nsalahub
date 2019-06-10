package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleCreateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_EMPTY_CONTENT_MESSAGE;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_EMPTY_TITLE_MESSAGE;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_LENGTH_CONTENT_MESSAGE;

@Component
public class ArticleValidator implements Validator {

    private static final Integer MAX_LENGTH_NEW_ARTICLE = 1000;

    @Override
    public boolean supports(Class<?> aClass) {
        return ArticleCreateDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "content", "", VALIDATION_EMPTY_CONTENT_MESSAGE);
        ValidationUtils.rejectIfEmpty(errors, "title", "", VALIDATION_EMPTY_TITLE_MESSAGE);

        ArticleCreateDTO articleCreateDTO = (ArticleCreateDTO) o;

        if (articleCreateDTO.getContent().length() > MAX_LENGTH_NEW_ARTICLE) {
            errors.rejectValue("content", "", VALIDATION_LENGTH_CONTENT_MESSAGE);
        }
    }
}
