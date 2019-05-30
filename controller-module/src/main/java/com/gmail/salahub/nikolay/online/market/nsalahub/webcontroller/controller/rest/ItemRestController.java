package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller.rest;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.ItemService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.article.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemRestController {

    private final ItemService itemService;

    @Autowired
    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/api/items")
    public ResponseEntity<List<ItemDTO>> showArticles() {
        List<ItemDTO> articles = itemService.getByPageNumber(1);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/api/items/{id}")
    private ResponseEntity<ItemDTO> showItem(
            @PathVariable(name = "id") Long id) {
        ItemDTO itemDTO = itemService.getById(id);
        if (itemDTO != null) {
            return new ResponseEntity<>(itemDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/items")
    private ResponseEntity createItem(@RequestBody ItemDTO itemDTO) {
        itemService.create(itemDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
