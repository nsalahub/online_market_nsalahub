package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.ArticleService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.CommentService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.CommentDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.UserPrincipal;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleCreateDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.ArticleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_ARTICLE_SALE_URL;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_UPDATE_ARTICLE_SALE_URL;

@Controller
@RequestMapping("/public")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;
    private final CommentService commentService;
    private final ArticleValidator articleValidator;

    @Autowired
    private ArticleController(ArticleService articleService,
                              CommentService commentService,
                              ArticleValidator articleValidator) {
        this.articleValidator = articleValidator;
        this.commentService = commentService;
        this.articleService = articleService;
    }

    @GetMapping("/article/sale")
    public String showArticleSalePage(
            @RequestParam(defaultValue = "1", value = "currentPage") Integer page,
            Model model) {
        int valueOfPages = articleService.getNumberOfPages();
        model.addAttribute("numberPage", valueOfPages);
        List<ArticleDTO> articles = articleService.getByPageNumber(page);
        model.addAttribute("articles", articles);
        logger.info("showing articles for sale");
        return "articlesale";
    }

    @GetMapping("/article/customer")
    public String showArticleCustomerPage(
            @RequestParam(defaultValue = "1", value = "currentPage") Integer page,
            Model model) {
        int valueOfPages = articleService.getNumberOfPages();
        model.addAttribute("numberPage", valueOfPages);
        List<ArticleDTO> articles = articleService.getByPageNumber(page);
        model.addAttribute("articles", articles);
        logger.info("showing articles for customer");
        return "articlecustomer";
    }

    @PostMapping("/article/sale/delete")
    public String deleteThisArticle(ArticleDTO articleDTO) {
        articleService.delete(articleDTO.getId());
        logger.info("start deleted some article" + articleDTO.getId());
        return REDIRECT_ARTICLE_SALE_URL;
    }

    @GetMapping("/article/sale/new")
    public String showAddArticlePage(Model model) {
        ArticleCreateDTO articleCreateDTO = new ArticleCreateDTO();
        model.addAttribute("article", articleCreateDTO);
        logger.info("start showing article add page for sale");
        return "addarticlepage";
    }

    @PostMapping("/article/sale/new")
    public String saveNewArticle(
            @ModelAttribute(value = "article") ArticleCreateDTO articleCreateDTO,
                                 BindingResult bindingResult,
                                 Model model) throws ParseException {
        articleValidator.validate(articleCreateDTO, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("article", articleCreateDTO);
            return "addarticlepage";
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleCreateDTO.setUsername(((UserPrincipal) userDetails).getUsername());
        articleService.create(articleCreateDTO);
        logger.info("start saving " + articleCreateDTO.getContent() + " content"
                + articleCreateDTO.getUsername());
        return REDIRECT_ARTICLE_SALE_URL;
    }

    @GetMapping("/article/sale/more")
    public String showThisArticleSale(
            @RequestParam(value = "article") Long article,
            Model model) {
        ArticleDTO articleDTO = articleService.getArticleById(article);
        model.addAttribute("article", articleDTO);
        ArticleDTO articleDeleteDTO = new ArticleDTO();
        model.addAttribute("articleDeleteDTO", articleDeleteDTO);
        logger.info("start showing more information about article with id " + article);
        return "showarticlesale";
    }

    @PostMapping("/article/sale/update")
    public String updateArticle(ArticleDTO articleDTO) {
        articleService.update(articleDTO);
        logger.info("start updating article" + articleDTO.getContent());
        return REDIRECT_UPDATE_ARTICLE_SALE_URL + articleDTO.getId();
    }

    @GetMapping("/article/customer/more")
    public String showThisArticleCustomer(
            @RequestParam(value = "article") Long article,
            Model model) {
        ArticleDTO articleDTO = articleService.getArticleById(article);
        model.addAttribute("article", articleDTO);
        logger.info("start showing more information about article with id " + articleDTO.getId());
        return "showarticlecustomer";
    }

    @PostMapping("/article/sale/comment/delete")
    public String deleteCommentThisArticle(CommentDTO commentDTO) {
        commentService.delete(commentDTO);
        logger.info("start deleted comment with content" + commentDTO.getContent());
        return REDIRECT_UPDATE_ARTICLE_SALE_URL + commentDTO.getArticleDTO().getId();
    }
}
