package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.ItemService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.OrderService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.PurchaseDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.UserPrincipal;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_ITEMS_CUSTOMER_URL;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_ITEMS_SALE_URL;

@Controller
@RequestMapping("/public")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public ItemController(ItemService itemService,
                          OrderService orderService,
                          UserService userService) {
        this.userService = userService;
        this.orderService = orderService;
        this.itemService = itemService;
    }

    @GetMapping("/items/sale")
    public String showItemsSalePage(@RequestParam(defaultValue = "1", value = "currentPage")
                                            Integer page, Model model) {
        List<ItemDTO> itemList = itemService.getByPageNumber(page);
        model.addAttribute("items", itemList);
        int valueOfPages = itemService.getNumberOfPages();
        model.addAttribute("numberPage", valueOfPages);
        return "itemssale";
    }

    @GetMapping("/items/sale/more")
    public String showThisItemSale(
            @RequestParam(value = "item") Long item,
            Model model) {
        ItemDTO itemDTO = itemService.getById(item);
        model.addAttribute("item", itemDTO);
        return "showitem";
    }

    @PostMapping("/items/sale/delete")
    public String deleteItem(ItemDTO itemDTO) {
        itemService.deleteById(itemDTO.getId());
        return REDIRECT_ITEMS_SALE_URL;
    }

    @GetMapping("/items/customer")
    public String showItemsCustomerPage(@RequestParam(defaultValue = "1", value = "currentPage")
                                                Integer page, Model model) {
        List<ItemDTO> itemList = itemService.getByPageNumber(page);
        model.addAttribute("items", itemList);
        int valueOfPages = itemService.getNumberOfPages();
        model.addAttribute("numberPage", valueOfPages);
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        model.addAttribute(purchaseDTO);
        return "itemscustomer";
    }

    @GetMapping("/items/customer/more")
    public String showThisItemCustomer(
            @RequestParam(value = "item") Long item,
            Model model) {
        ItemDTO itemDTO = itemService.getById(item);
        model.addAttribute("item", itemDTO);
        return "showitem";
    }

    @PostMapping("/items/customer/buy")
    public String buyItem(
            PurchaseDTO purchaseDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.getById(((UserPrincipal) userDetails).getIdFromUserPrincipal());
        purchaseDTO.setUserDTO(userDTO);
        orderService.create(purchaseDTO);
        logger.info("start creating user");
        return REDIRECT_ITEMS_CUSTOMER_URL;
    }
}
