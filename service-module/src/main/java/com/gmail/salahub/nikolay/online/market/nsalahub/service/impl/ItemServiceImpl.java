package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.ItemRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.ItemService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.PageService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.ParserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ItemConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ItemXMLConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UploadItemConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemUploadDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.parser.ItemXmlDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.transaction.Transactional;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.salahub.nikolay.online.market.nsalahub.repository.constant.RepositoryConstant.LIMIT_ITEM_VALUE;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemConverter itemConverter;
    private final PageService pageService;
    private final ParserService parserService;
    private final ItemXMLConverter itemXMLConverter;
    private final UploadItemConverter uploadItemConverter;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           ItemConverter itemConverter,
                           PageService pageService,
                           ParserService parserService,
                           ItemXMLConverter itemXMLConverter,
                           @Qualifier("itemUploadConverter") UploadItemConverter uploadItemConverter) {
        this.uploadItemConverter = uploadItemConverter;
        this.itemXMLConverter = itemXMLConverter;
        this.parserService = parserService;
        this.itemConverter = itemConverter;
        this.itemRepository = itemRepository;
        this.pageService = pageService;
    }

    @Override
    @Transactional
    public List<ItemDTO> getByPageNumber(Integer page) {
        List<Item> items = itemRepository.findAllWhereDeletedFalse(pageService
                .getLimitValue(LIMIT_ITEM_VALUE, page), LIMIT_ITEM_VALUE);
        List<ItemDTO> itemDTOS = items.stream()
                .map(itemConverter::toDTO)
                .collect(Collectors.toList());
        return itemDTOS;
    }

    @Override
    @Transactional
    public int getNumberOfPages() {
        Integer valueOfUsers = itemRepository.getCountOfEntitiesWhereDeletedFalse();
        Integer valueOfPages = pageService.getValueOfPages(valueOfUsers, LIMIT_ITEM_VALUE);
        return valueOfPages;
    }

    @Override
    @Transactional
    public ItemDTO getById(Long id) {
        Item item = itemRepository.findById(id);
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

    @Override
    @Transactional
    public void upload(InputStream xmlInputStream) throws IOException, SAXException, ParserConfigurationException {
        List<ItemUploadDTO> itemDTOS = parserService.parseInputStream(xmlInputStream);
        List<Item> items = itemDTOS.stream()
                .map(uploadItemConverter::fromDTO)
                .collect(Collectors.toList());
        for(Item item : items){
            itemRepository.persist(item);
        }
    }
}
