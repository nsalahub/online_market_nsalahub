package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Order;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.order.OrderDTO;

public interface OrderConverter {

    Order fromDTO(OrderDTO orderDTO);

    OrderDTO toDTO(Order order);
}
