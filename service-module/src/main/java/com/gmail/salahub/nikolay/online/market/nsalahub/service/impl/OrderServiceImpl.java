package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.ItemRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.OrderRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.UserRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Item;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Order;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Status;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.User;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.ItemService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.OrderService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.PageService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ItemConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.OrderConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.PurchaseDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.order.OrderDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.order.UpdateOrderDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.gmail.salahub.nikolay.online.market.nsalahub.repository.constant.RepositoryConstant.LIMIT_ORDER_VALUE;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final PageService pageService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    private final ItemRepository itemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderConverter orderConverter,
                            PageService pageService,
                            UserService userService,
                            UserRepository userRepository,
                            UserConverter userConverter,

                            ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.userService = userService;
        this.orderConverter = orderConverter;
        this.orderRepository = orderRepository;
        this.pageService = pageService;

    }

    @Override
    @Transactional
    public List<OrderDTO> getByPageNumber(int page) {
        List<OrderDTO> userDTOS;
        List<Order> users = orderRepository.findAll(pageService
                .getLimitValue(LIMIT_ORDER_VALUE, page), LIMIT_ORDER_VALUE);
        userDTOS = users.stream()
                .map(orderConverter::toDTO)
                .collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    @Transactional
    public int getNumberOfPages() {
        Integer valueOfUsers = orderRepository.getCountOfEntities();
        Integer valueOfPages = pageService.getValueOfPages(valueOfUsers, LIMIT_ORDER_VALUE);
        return valueOfPages;
    }

    @Override
    @Transactional
    public OrderDTO getById(Long id) {
        Order order = orderRepository.findById(id);
        UserDTO userWithoutProfile = userService.getById(id);
        UserDTO resultUserDTO = userService.getByUsername(userWithoutProfile.getEmail());
        OrderDTO orderDTO = orderConverter.toDTO(order);
        orderDTO.setUserDTO(resultUserDTO);
        return orderDTO;
    }

    @Override
    @Transactional
    public void update(UpdateOrderDTO updateOrderDTO) {
        Order order = orderRepository.findById(Long.valueOf(updateOrderDTO.getId()));
        order.setStatus(Status.valueOf(updateOrderDTO.getStatus()));
        orderRepository.merge(order);
    }

    @Override
    @Transactional
    public void create(PurchaseDTO purchaseDTO) {
        Item item= itemRepository.findById(Long.valueOf(purchaseDTO.getId()));
        Order order = new Order();
        User user = userRepository.findById(purchaseDTO.getUserDTO().getId());
        order.setUser(user);
        UUID uuid = UUID.randomUUID();
        order.setNumberOrder(uuid.toString());
        order.setItem(item);
        order.setDateCreated(new Date());
        order.setTotalPrice(getTotalPrice(item.getPrice(),
                Integer.valueOf(purchaseDTO.getQuantity())));
        order.setTotalPrice(new BigDecimal(2.2));
        order.setDeleted(false);
        order.setStatus(Status.NEW);
        order.setQuantity(Integer.valueOf(purchaseDTO.getQuantity()));
        orderRepository.persist(order);
    }

    @Override
    @Transactional
    public int getNumberOfPagesByUserId(Long id) {
        Integer valueOfUsers = orderRepository.getCountOfEntitiesByUserId(id);
        Integer valueOfPages = pageService.getValueOfPages(valueOfUsers, LIMIT_ORDER_VALUE);
        return valueOfPages;
    }

    @Override
    @Transactional
    public List<OrderDTO> getByPageNumberByUserId(Integer page, Long id) {
        List<OrderDTO> userDTOS;
        List<Order> users = orderRepository.findAllByUserId(pageService
                .getLimitValue(LIMIT_ORDER_VALUE, page), LIMIT_ORDER_VALUE , id);
        userDTOS = users.stream()
                .map(orderConverter::toDTO)
                .collect(Collectors.toList());
        return userDTOS;
    }

    private BigDecimal getTotalPrice(BigDecimal price, Integer quantity) {
        BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(quantity));
        return totalPrice;
    }
}
