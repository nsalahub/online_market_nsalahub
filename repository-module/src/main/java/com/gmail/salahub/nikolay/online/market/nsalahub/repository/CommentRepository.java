package com.gmail.salahub.nikolay.online.market.nsalahub.repository;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Comment;

import java.util.List;

public interface CommentRepository extends GenericRepository<Long, Comment> {
    List<Comment> getByArticleId(Long id);
}
