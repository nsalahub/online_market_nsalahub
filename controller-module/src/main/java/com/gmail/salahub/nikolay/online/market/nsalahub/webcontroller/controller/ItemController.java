package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.ItemService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_ITEMS_URL;

@Controller
@RequestMapping("/public")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String showItemsPage(@RequestParam(defaultValue = "1", value = "currentPage")
                                        Integer page, Model model) {
        List<ItemDTO> itemList = itemService.getByPageNumber(page);
        model.addAttribute("items",itemList);
        int valueOfPages = itemService.getNumberOfPages();
        model.addAttribute("numberPage", valueOfPages);
        return "items";
    }

    @GetMapping("/items/more")
    public String showThisArticleSale(
            @RequestParam(value = "item") Long item,
            Model model) {
        ItemDTO itemDTO = itemService.getById(item);
        model.addAttribute("item", itemDTO);
        return "showitem";
    }

    @PostMapping("/items/delete")
    public String deleteItem(ItemDTO itemDTO){
        itemService.deleteById(itemDTO.getId());
        return  REDIRECT_ITEMS_URL;
    }
}
