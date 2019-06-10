package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.ItemService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.OrderService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.PurchaseDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.UserPrincipal;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.exception.UploadFileItemsException;
import com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.ItemXMLValidator;
import com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.PurchaseValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.Part;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_ITEMS_CUSTOMER_URL;
import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_ITEMS_SALE_URL;

@Controller
@RequestMapping("/public")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private static final String UPLOAD_FILE_ERROR_MESSAGE = "Upload fail is failed";
    private static final Integer FIRST_PAGE = 1;

    private final ItemService itemService;
    private final OrderService orderService;
    private final UserService userService;
    private final PurchaseValidator purchaseValidator;
    private final ItemXMLValidator itemXMLValidator;

    @Autowired
    public ItemController(ItemService itemService,
                          OrderService orderService,
                          UserService userService,
                          PurchaseValidator purchaseValidator,
                          ItemXMLValidator itemXMLValidator) {
        this.itemXMLValidator = itemXMLValidator;
        this.userService = userService;
        this.orderService = orderService;
        this.itemService = itemService;
        this.purchaseValidator = purchaseValidator;
    }

    @GetMapping("/items/sale")
    public String showItemsSalePage(@RequestParam(defaultValue = "1", value = "currentPage")
                                            Integer page, Model model) {
        showItems(page, model);
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

    @PostMapping("/items/upload")
    public String uploadFileXML(@RequestParam("xml") MultipartFile part, Model model) {
        try (InputStream validateStream = part.getInputStream()) {
            if (itemXMLValidator.validate(validateStream)) {
                try (InputStream readStream = part.getInputStream()) {
                    itemService.upload(readStream);
                } catch (SAXException e) {
                    logger.error(e.getMessage());
                } catch (ParserConfigurationException e) {
                    logger.error(e.getMessage());
                }
            } else {
                model.addAttribute("validMessage", "Your file is not valid");
                showItems(FIRST_PAGE, model);
                return "itemssale";
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new UploadFileItemsException(UPLOAD_FILE_ERROR_MESSAGE, e);
        }
        return REDIRECT_ITEMS_SALE_URL;
    }

    @PostMapping("/items/customer/buy")
    public String buyItem(
            PurchaseDTO purchaseDTO,
            BindingResult bindingResult, Model model) {
        purchaseValidator.validate(purchaseDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return REDIRECT_ITEMS_CUSTOMER_URL;
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.getById(((UserPrincipal) userDetails).getIdFromUserPrincipal());
        purchaseDTO.setUserDTO(userDTO);
        orderService.create(purchaseDTO);
        logger.info("start creating user");
        return REDIRECT_ITEMS_CUSTOMER_URL;
    }

    private void showItems(Integer page, Model model) {
        List<ItemDTO> itemList = itemService.getByPageNumber(page);
        model.addAttribute("items", itemList);
        int valueOfPages = itemService.getNumberOfPages();
        model.addAttribute("numberPage", valueOfPages);
    }
}
