package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.ProductItem;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.additional.Cart;
import ru.kpfu.itis.model.additional.OrderStatus;
import ru.kpfu.itis.model.additional.TransactionInform;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.repository.OrderRepository;
import ru.kpfu.itis.service.repository.UserRepository;

/**
 * Created by vladislav on 04.05.17.
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public TransactionInform createOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        orderRepository.saveAndFlush(order);
        return new TransactionInform(true, "OK");
    }

    @Override
    public TransactionInform updateOrder(Long orderId, ProductItem productItem) {
        Order order = orderRepository.findOne(orderId);
        order.getProducts().add(productItem);
        orderRepository.saveAndFlush(order);
        return new TransactionInform(true,"OK");
    }

    @Override
    public TransactionInform changeStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findOne(orderId);
        order.setOrderStatus(status);
        orderRepository.saveAndFlush(order);
        return new TransactionInform(true,"OK");
    }

    @Override
    public void createOrderFromCart(Cart cart) {
        Order order = new Order();
        order.setUser(userRepository
                .findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setProducts(cart.getProductItems());
        orderRepository.saveAndFlush(order);
    }
}
