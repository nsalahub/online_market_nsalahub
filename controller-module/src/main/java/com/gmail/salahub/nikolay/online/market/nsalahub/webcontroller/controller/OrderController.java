package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.OrderService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.UserPrincipal;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.order.OrderDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.order.UpdateOrderDTO;
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

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_ORDERS_URL;

@Controller
@RequestMapping("/public")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService,
                           UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/orders")
    public String showOrders(@RequestParam(defaultValue = "1", value = "currentPage")
                                     Integer page, Model model) {
        int valueOfPages = orderService.getNumberOfPages();
        model.addAttribute("numberPage", valueOfPages);
        List<OrderDTO> orderDTOList = orderService.getByPageNumber(page);
        model.addAttribute("orders", orderDTOList);
        logger.info("stat showing orders");
        return "orders";
    }

    @GetMapping("/order/more")
    public String showThisArticleSale(
            @RequestParam(value = "order") Long order,
            Model model) {
        OrderDTO orderDTO = orderService.getById(order);
        model.addAttribute("order", orderDTO);
        return "showorder";
    }

    @PostMapping("/order/update")
    public String updateItem(
            UpdateOrderDTO updateOrderDTO) {
        orderService.update(updateOrderDTO);
        logger.info(updateOrderDTO.getStatus() + " status success changed");
        return REDIRECT_ORDERS_URL + updateOrderDTO.getId();
    }

    @GetMapping("orders/customer")
    public String showOrdersThisUser(@RequestParam(defaultValue = "1", value = "currentPage")
                                             Integer currentPage, Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.getById(((UserPrincipal) userDetails).getIdFromUserPrincipal());
        int valueOfPages = orderService.getNumberOfPagesByUserId(userDTO.getId());
        model.addAttribute("numberPage", valueOfPages);
        List<OrderDTO> orderDTOList = orderService.getByPageNumberByUserId(currentPage, userDTO.getId());
        model.addAttribute("orders", orderDTOList);
        logger.info("stat showing orders");
        return "showorderscustomer";
    }
}
