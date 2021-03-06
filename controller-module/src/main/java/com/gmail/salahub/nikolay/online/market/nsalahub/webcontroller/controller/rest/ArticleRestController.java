package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller.rest;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.ArticleService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.UserPrincipal;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleRestController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleRestController.class);

    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public ArticleRestController(ArticleService articleService,
                                 UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping("/api/articles")
    public ResponseEntity saveArticle(@RequestBody ArticleDTO articleDTO
    ) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.getById(((UserPrincipal)userDetails).getIdFromUserPrincipal());
        articleDTO.setUserDTO(userDTO);
        articleService.save(articleDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleDTO> showArticleWithId(
            @PathVariable(name = "id")
                    Long id) {
        ArticleDTO articleDTO = articleService.getArticleById(id);
        if (articleDTO != null) {
            return new ResponseEntity<>(articleDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleDTO>> showArticles() {
        List<ArticleDTO> articles = articleService.getByPageNumber(1);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity deleteById(
            @PathVariable(name = "id") Long id) {
        articleService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
