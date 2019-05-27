package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.CommentRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Comment;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.CommentService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.CommentDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public void delete(CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getId());
        comment.setDeleted(true);
        commentRepository.merge(comment);
    }
}
