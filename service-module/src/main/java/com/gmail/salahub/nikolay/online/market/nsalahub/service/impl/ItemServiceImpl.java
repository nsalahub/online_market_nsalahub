package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.ItemRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.ItemService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.PageService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ItemConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.salahub.nikolay.online.market.nsalahub.repository.constant.RepositoryConstant.LIMIT_ITEM_VALUE;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemConverter itemConverter;
    private final PageService pageService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           ItemConverter itemConverter,
                           PageService pageService) {
        this.itemConverter = itemConverter;
        this.itemRepository = itemRepository;
        this.pageService = pageService;
    }

    @Override
    @Transactional
    public List<ItemDTO> getByPageNumber(Integer page) {
        List<Item> items = itemRepository.findAll((page - 1) * LIMIT_ITEM_VALUE, LIMIT_ITEM_VALUE);
        List<ItemDTO> itemDTOS = items.stream()
                .map(itemConverter::toDTO)
                .collect(Collectors.toList());
        return itemDTOS;
    }

    @Override
    @Transactional
    public int getNumberOfPages() {
        Integer valueOfUsers = itemRepository.getCountOfEntities();
        Integer valueOfPages = pageService.getValueOfPages(valueOfUsers, LIMIT_ITEM_VALUE);
        return valueOfPages;
    }

    @Override
    @Transactional
    public ItemDTO getById(Long id) {
        Item item =  itemRepository.findById(id);
        ItemDTO itemDTO = itemConverter.toDTO(item);
        return itemDTO;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void create(ItemDTO itemDTO) {
        Item item = itemConverter.fromDTO(itemDTO);
        itemRepository.persist(item);
    }
}
