package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.exception.NoResultUserServiceException;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.EMAIL_USER_REGEX;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_EMAIL_USER_EMPTY_MESSAGE;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_EMAIL_USER_EXIST_MESSAGE;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_EMAIL_USER_INVALID_MESSAGE;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_NAME_USER_MESSAGE;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_SURNAME_USER_MESSAGE;

@Component
public class UserValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", VALIDATION_NAME_USER_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "", VALIDATION_SURNAME_USER_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", VALIDATION_EMAIL_USER_EMPTY_MESSAGE);

        UserDTO userDTO = (UserDTO) o;

        Pattern pattern;

        pattern = Pattern.compile(EMAIL_USER_REGEX,
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(userDTO.getEmail()).matches())) {
            errors.rejectValue("email", "", VALIDATION_EMAIL_USER_INVALID_MESSAGE);
        }
        try {
            userService.getByUsername(userDTO.getEmail());
            errors.rejectValue("email", "", VALIDATION_EMAIL_USER_EXIST_MESSAGE);
        } catch (NoResultUserServiceException e) {
            logger.info(userDTO.getEmail() + " this email no exist in database");
        }

    }
}
