package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator;


import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.PurchaseDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant.ValidatorConstant.VALIDATION_QUANTITY_PURCHASE_MESSAGE;

@Component
public class PurchaseValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PurchaseDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "quantity", "", VALIDATION_QUANTITY_PURCHASE_MESSAGE);
    }
}
