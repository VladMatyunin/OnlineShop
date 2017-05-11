package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.additional.Cart;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.repository.OrderRepository;
import ru.kpfu.itis.service.repository.UserRepository;
import ru.kpfu.itis.service.security.MyUserDetail;

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
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String createOrder(){
        orderService.createOrderFromCart((Cart) httpServletRequest.getSession().getAttribute("cart"));
        httpServletRequest.getSession().setAttribute("cart", new Cart());
        return "redirect:/profile";
    }
    @ResponseBody
    @RequestMapping(value = "/{id}")
    public ModelAndView loadOrderDetails(@PathVariable("id") Long id){
        Order order = orderRepository.findOne(id);
        User currentUser = userRepository
                .findByEmail(((MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());

        if (!order.getUser().getEmail().equals(currentUser.getEmail())) return new ModelAndView("404");
        ModelAndView modelAndView = new ModelAndView("orderItem");
        modelAndView.addObject("order", order);
        return modelAndView;
    }
}
