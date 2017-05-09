package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.repository.OrderRepository;
import ru.kpfu.itis.service.repository.UserRepository;

/**
 * Created by vladislav on 07.05.17.
 */
@Controller
@RequestMapping(value = "/profile")
public class ProfileController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @RequestMapping(value = "")
    public String loadProfile(ModelMap modelMap){
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        modelMap.addAttribute("orders", orderRepository.findAllByUser(user));
        modelMap.addAttribute("userInfo", user);
        return "profile";
    }
}
