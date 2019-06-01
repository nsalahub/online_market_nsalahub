package com.gmail.salahub.nikolay.testmodel;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Article;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Comment;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Profile;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Role;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Review;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.User;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.CommentDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ProfileDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ReviewsDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.RoleDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleCreateDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.review.ReviewDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UpdateUserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static java.util.Arrays.asList;

public class TestModel {

    public static Role getTestRole() {
        Role role = new Role();
        role.setId(1L);
        role.setName("SALE");
        return role;
    }

    public static RoleDTO getTestRoleDTO() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setName("SALE");
        return roleDTO;
    }

    public static User getTestUser() {
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setSurname("surname");
        user.setPatronymic("patronymic");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        Role role = getTestRole();
        user.setRole(role);
        user.setDeleted(true);
        return user;
    }

    public static UserDTO getTestUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("name");
        userDTO.setSurname("surname");
        userDTO.setPatronymic("patronymic");
        userDTO.setEmail("email@gmail.com");
        userDTO.setPassword("password");
        RoleDTO role = getTestRoleDTO();
        userDTO.setRoleDTO(role);
        userDTO.setDeleted(true);
        return userDTO;
    }

    public static UpdateUserDTO getTestUpdateUserDTO() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setEmail("email@gmail.com");
        updateUserDTO.setRole("ADMINISTRATOR");
        return updateUserDTO;
    }

    public static Review getTestReview() {
        Review review = new Review();
        review.setId(1L);
        review.setDeleted(false);
        review.setCreatingDate(new Date(2223 - 22 - 22));
        review.setShowing(true);
        review.setUser(getTestUser());
        review.setReview("review");
        return review;
    }

    public static ReviewDTO getTestReviewDTO() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(1L);
        reviewDTO.setDeleted(false);
        reviewDTO.setCreatingDate(new Date(2223 - 22 - 22));
        reviewDTO.setShowing(true);
        reviewDTO.setUserDTO(getTestUserDTO());
        reviewDTO.setReview("review");
        return reviewDTO;
    }

    public static Article getTestArticle() {
        Article article = new Article();
        article.setId(1L);
        article.setUser(getTestUser());
        article.setTitle("title");
        article.setDate(new Date(1112 - 22 - 22));
        article.setContent("content");
        article.setDeleted(false);
        return article;
    }

    public static ArticleDTO getTestArticleDTO() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(1L);
        articleDTO.setUserDTO(getTestUserDTO());
        articleDTO.setTitle("title");
        articleDTO.setDate(new Date(1112 - 22 - 22));
        articleDTO.setContent("content");
        return articleDTO;
    }

    public static Comment getTestComment() {
        Comment comment = new Comment();
        comment.setUser(getTestUser());
        comment.setId(1L);
        comment.setDeleted(false);
        comment.setDate(new Date(2222 - 22 - 22));
        comment.setArticle(getTestArticle());
        return comment;
    }

    public static CommentDTO getTestCommentDTO() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUserDTO(getTestUserDTO());
        commentDTO.setId(1L);
        commentDTO.setDeleted(false);
        commentDTO.setDate(new Date(2222 - 22 - 22));
        commentDTO.setArticleDTO(getTestArticleDTO());
        return commentDTO;
    }

    public static Item getTestItem() {
        Item item = new Item();
        item.setId(1L);
        item.setName("name");
        item.setPrice(new BigDecimal(2.2));
        item.setDeleted(false);
        item.setUniqueNumber("unique_number");
        item.setDescription("description");
        return item;
    }

    public static ItemDTO getTestItemDTO() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(1L);
        itemDTO.setName("name");
        itemDTO.setPrice(new BigDecimal(2.2));
        itemDTO.setDeleted(false);
        itemDTO.setUniqueNumber("unique_number");
        itemDTO.setDescription("description");
        return itemDTO;
    }

    public static Profile getTestProfile() {
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setTelephone("telephone");
        profile.setUser(getTestUser());
        profile.setDeleted(false);
        profile.setAddress("address");
        return profile;
    }

    public static ProfileDTO getTestProfileDTO() {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(1L);
        profileDTO.setTelephone("telephone");
        profileDTO.setUserDTO(getTestUserDTO());
        profileDTO.setDeleted(false);
        profileDTO.setAddress("address");
        return profileDTO;
    }

    public static ArticleCreateDTO getTestArticleCreateDTO() {
        ArticleCreateDTO articleCreateDTO = new ArticleCreateDTO();
        articleCreateDTO.setContent("content");
        articleCreateDTO.setId(1L);
        articleCreateDTO.setDate(new Date(2222 - 22 - 22));
        articleCreateDTO.setTitle("title");
        articleCreateDTO.setUsername("userName");
        return articleCreateDTO;
    }

    public static ReviewsDTO getTestReviews() {
        ReviewsDTO reviewsDTO = new ReviewsDTO();
        List<ReviewDTO> reviewDTOS = asList(getTestReviewDTO(), getTestReviewDTO());
        reviewsDTO.setReviewList(reviewDTOS);
        return reviewsDTO;
    }

    public static UserDTO getTestUserForRest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("name");
        userDTO.setSurname("surname");
        userDTO.setEmail("test@maielsssssssssssdasd");
        userDTO.setRoleDTO(getTestRoleDTO());
        return userDTO;
    }

    public static ReviewDTO getTestReviewDTOWithIdForDeleete() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(1L);
        return reviewDTO;
    }

    public static UserDTO getTestUserForSaving(){
        UserDTO userDTO = new UserDTO();
        userDTO.setRoleDTO(getTestRoleDTO());
        userDTO.setSurname("surname");
        userDTO.setName("name");
        userDTO.setPatronymic("patr");
        userDTO.setEmail("email234234234234234@email");
        return userDTO;
    }
}
