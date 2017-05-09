package ru.kpfu.itis.service;

import ru.kpfu.itis.model.ProductItem;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.additional.Cart;
import ru.kpfu.itis.model.additional.OrderStatus;
import ru.kpfu.itis.model.additional.TransactionInform;

/**
 * Created by vladislav on 04.05.17.
 */
public interface OrderService {
    TransactionInform createOrder(User user);
    TransactionInform updateOrder(Long orderId, ProductItem productItem);
    TransactionInform changeStatus(Long orderId, OrderStatus status);
    void createOrderFromCart(Cart cart);
}
