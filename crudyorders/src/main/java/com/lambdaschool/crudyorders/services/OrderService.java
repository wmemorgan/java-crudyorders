package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Order;

public interface OrderService {

    Order save(Order order);

    Order findById(long id);
}
