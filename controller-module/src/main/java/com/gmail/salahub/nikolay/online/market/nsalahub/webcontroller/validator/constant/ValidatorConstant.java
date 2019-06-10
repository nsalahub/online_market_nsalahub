package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.constant;

public class ValidatorConstant {
    private ValidatorConstant() {
    }

    public static final String VALIDATION_NAME_USER_MESSAGE = "Field name is empty";
    public static final String VALIDATION_SURNAME_USER_MESSAGE = "Field surname is empty";
    public static final String VALIDATION_EMAIL_USER_EMPTY_MESSAGE = "Field email is empty";
    public static final String VALIDATION_EMAIL_USER_INVALID_MESSAGE = "Email id not valid";
    public static final String VALIDATION_EMAIL_USER_EXIST_MESSAGE = "Sorry, this email is exist";

    public static final String VALIDATION_QUANTITY_PURCHASE_MESSAGE = "Uoy mast select quantity";

    public static final String VALIDATION_REVIEW_REVIEW_MESSAGE = "Uoy dont create review";

    public static final String VALIDATION_EMPTY_TELEPHONE_PROFILE_MESSAGE = "field Telephone is empty";
    public static final String VALIDATION_INVALID_TELEPHONE_MESSAGE = "This telephone is not valid";
    public static final String VALIDATION_EMPTY_ADDRESS_MESSAGE = "Field address id empty";

    public static final String VALIDATION_EMPTY_TITLE_MESSAGE = "Field with title is empty";
    public static final String VALIDATION_EMPTY_CONTENT_MESSAGE = "Field content is empty";
    public static final String VALIDATION_LENGTH_CONTENT_MESSAGE = "Content field has big length";

    public static final String EMAIL_USER_REGEX = "^(.+)@(.+)$";
    public static final String PHONE_NUMBER_REGEX = "^\\\\+375[0-9]{9}$\"";
}
