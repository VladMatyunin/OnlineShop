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
import ru.kpfu.itis.service.security.MyUserDetail;

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
        User currentUser = userRepository
                .findByEmail(((MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
        modelMap.addAttribute("orders", orderRepository.findAllByUser(currentUser));
        modelMap.addAttribute("userInfo", currentUser);
        return "profile";
    }
}
