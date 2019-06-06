package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.PHONE_NUMBER_REGEX;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_EMPTY_ADDRESS_MESSAGE;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_EMPTY_TELEPHONE_PROFILE_MESSAGE;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_INVALID_TELEPHONE_MESSAGE;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_NAME_USER_MESSAGE;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_SURNAME_USER_MESSAGE;

@Component
public class ProfileValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "profileDTO.telephone", "",
                VALIDATION_EMPTY_TELEPHONE_PROFILE_MESSAGE);
        ValidationUtils.rejectIfEmpty(errors, "profileDTO.address", "",
                VALIDATION_EMPTY_ADDRESS_MESSAGE);
        ValidationUtils.rejectIfEmpty(errors, "name", "", VALIDATION_NAME_USER_MESSAGE);
        ValidationUtils.rejectIfEmpty(errors, "surname", "", VALIDATION_SURNAME_USER_MESSAGE);

        UserDTO userDTO = (UserDTO) o;

        Pattern pattern;

        pattern = Pattern.compile(PHONE_NUMBER_REGEX, Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(userDTO.getProfileDTO().getTelephone()).matches())) {
            errors.rejectValue("profileDTO.telephone", "", VALIDATION_INVALID_TELEPHONE_MESSAGE);
        }
    }
}
