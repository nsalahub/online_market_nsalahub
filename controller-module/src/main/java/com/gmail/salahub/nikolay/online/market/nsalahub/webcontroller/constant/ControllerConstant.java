package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant;

public class ControllerConstant {
    private ControllerConstant() {
    }

    public static final String ADMINISTRATOR_ROLE_STRING_VALUE = "ADMINISTRATOR";
    public static final String CUSTOMER_ROLE_STRING_VALUE = "CUSTOMER";
    public static final String SECURE_REST_API_ROLE_VALUE = "SECURE_REST_API";
    public static final String SALE_USER_ROLE_VALUE = "SALE";

    public static final String SHOW_USERS_URL = "/private/user";
    public static final String SHOW_ARTICLE_CUSTOMER_URL = "/public/article/customer";
    public static final String SHOW_ARTICLE_SALE_URL = "/public/article/sale";

    public static final String REDIRECT_USER_URL = "redirect:/private/user";
    public static final String REDIRECT_REVIEW_URL = "redirect:/private/review";
    public static final String REDIRECT_PROFILE_URL = "redirect:/public/profile";
    public static final String REDIRECT_ARTICLE_SALE_URL = "redirect:/public/article/sale";
    public static final String REDIRECT_UPDATE_ARTICLE_SALE_URL = "redirect:/public/article/sale/more?article=";
    public static final String REDIRECT_ITEMS_SALE_URL = "redirect:/public/items/sale";
    public static final String REDIRECT_ITEMS_CUSTOMER_URL = "redirect:/public/items/customer";
    public static final String REDIRECT_ORDERS_URL = "redirect:/public/order/more?order=";
    public static final String REDIRECT_ARTICLES_CUSTOMER_USER_URL = "redirect:/public/article/customer";
}
