package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.model.additional.Cart;
import ru.kpfu.itis.service.OrderService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vladislav on 04.05.17.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createOrder(){
        orderService.createOrderFromCart((Cart) httpServletRequest.getSession().getAttribute("cart"));
        return "redirect:/profile";
    }
}
