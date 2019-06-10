package com.gmail.salahub.nikolay.online.market.nsalahub.service;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.PurchaseDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.order.OrderDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.order.UpdateOrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getByPageNumber(int page);

    int getNumberOfPages();

    OrderDTO getById(Long id);

    void update(UpdateOrderDTO updateOrderDTO);

    void create(PurchaseDTO purchaseDTO);

    int getNumberOfPagesByUserId(Long id);

    List<OrderDTO> getByPageNumberByUserId(Integer page, Long id);
}
