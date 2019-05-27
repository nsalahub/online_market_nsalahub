package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.ArticleRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.CommentRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.UserRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.impl.GenericRepositoryImpl;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Article;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Comment;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.User;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.ArticleService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.PageService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ArticleConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleCreateDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.salahub.nikolay.online.market.nsalahub.repository.constant.RepositoryConstant.LIMIT_ARTICLE_VALUE;

@Service("articleService")
public class ArticleServiceImpl  implements ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;
    private final PageService pageService;
    private final UserRepository userRepository;
    private final ArticleConverter articleSaveConverter;
    private final CommentRepository commentRepository;
    private final ArticleConverter articleCommentsConverter;


    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
                              ArticleConverter articleConverter,
                              PageService pageService,
                              UserRepository userRepository,
                              @Qualifier("articleSaveConverter") ArticleConverter articleSaveConverter,
                              CommentRepository commentRepository,
                              @Qualifier("articleCommentsConverter") ArticleConverter articleCommentsConverter
    ) {
        this.articleCommentsConverter = articleCommentsConverter;
        this.commentRepository = commentRepository;
        this.articleSaveConverter = articleSaveConverter;
        this.articleConverter = articleConverter;
        this.articleRepository = articleRepository;
        this.pageService = pageService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public int getNumberOfPages() {
        Integer valueOfUsers = articleRepository.getCountOfEntities();
        Integer valueOfPages = pageService.getValueOfPages(valueOfUsers, LIMIT_ARTICLE_VALUE);
        return valueOfPages;
    }

    @Override
    @Transactional
    public List<ArticleDTO> getByPageNumber(int page) {
        List<ArticleDTO> articleDTOS;
        List<Article> articles = articleRepository.findAll((page - 1) * LIMIT_ARTICLE_VALUE,
                LIMIT_ARTICLE_VALUE);
        articleDTOS = articles.stream()
                .map(articleConverter::toDTO)
                .collect(Collectors.toList());
        return articleDTOS;
    }

    @Override
    @Transactional
    public ArticleDTO getArticleById(Long articleId) {
        Article article = articleRepository.findById(articleId);
        List<Comment> comments = commentRepository.getByArticleId(article.getId());
        article.setComments(comments);
        ArticleDTO articleDTO = articleCommentsConverter.toDTO(article);
        return articleDTO;
    }

    @Override
    @Transactional
    public void save(ArticleDTO articleDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        Article article = articleSaveConverter.fromDTO(articleDTO);
        article.setUser(user);
        articleRepository.persist(article);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void create(ArticleCreateDTO articleCreateDTO) throws ParseException {
        Article article = new Article();
        article.setDeleted(false);
        article.setDate(articleCreateDTO.getDate());
        article.setTitle(articleCreateDTO.getTitle());
        article.setUser(userRepository.findByEmail(articleCreateDTO.getUsername()));
        article.setContent(articleCreateDTO.getContent());
        articleRepository.persist(article);
    }

    @Override
    @Transactional
    public void update(ArticleDTO articleDTO) {
        Article article = articleRepository.findById(articleDTO.getId());
        article.setTitle(articleDTO.getTitle());
        article.setDate(new Date());
        article.setContent(articleDTO.getContent());
        articleRepository.merge(article);
    }


}