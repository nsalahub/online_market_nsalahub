package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Comment;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.CommentDTO;

public interface CommentConverter {
    Comment fromDTO(CommentDTO commentDTO);

    CommentDTO toDTO (Comment comment);
}
