package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.order;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Order;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ItemConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.OrderConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.order.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("orderComponent")
public class OrderConverterImpl implements OrderConverter {

    private final ItemConverter itemConverter;
    private final UserConverter userConverter;

    @Autowired
    public OrderConverterImpl(ItemConverter itemConverter
            , UserConverter userConverter) {
        this.itemConverter = itemConverter;
        this.userConverter = userConverter;
    }

    @Override
    public Order fromDTO(OrderDTO orderDTO) {
        Order order = new Order();
        order.setDeleted(orderDTO.isDeleted());
        order.setId(orderDTO.getId());
        order.setQuantity(orderDTO.getQuantity());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setUser(userConverter.fromDTO(orderDTO.getUserDTO()));
        order.setItem(itemConverter.fromDTO(orderDTO.getItemDTO()));
        order.setNumberOrder(orderDTO.getNumberOrder());
        order.setStatus(orderDTO.getStatus());
        order.setDateCreated(orderDTO.getDateCreated());
        return order;
    }

    @Override
    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setDeleted(order.isDeleted());
        orderDTO.setId(order.getId());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setUserDTO(userConverter.toDTO(order.getUser()));
        orderDTO.setItemDTO(itemConverter.toDTO(order.getItem()));
        orderDTO.setNumberOrder(order.getNumberOrder());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setDateCreated(order.getDateCreated());
        return orderDTO;
    }
}
