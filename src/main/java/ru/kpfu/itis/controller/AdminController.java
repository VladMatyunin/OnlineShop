package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.ProductDescription;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.repository.ProductRepository;
import ru.kpfu.itis.service.repository.UserRepository;

/**
 * Created by vladislav on 04.05.17.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/users")
    public String loadUsersPage(ModelMap modelMap){
        modelMap.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }
    @RequestMapping(value = "/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userRepository.delete(id);
        return "redirect:/admin/users";
    }
    @ResponseBody
    @RequestMapping(value = "/users/confirm/{id}")
    public void confirmUser(@PathVariable("id") Long id){
        User user = userRepository.findOne(id);
        userService.confirmUser(user);
    }
    @RequestMapping(value = "/products")
    public String loadProducts(ModelMap modelMap){
        modelMap.addAttribute("products", productRepository.findAll());
        return "admin/products";
    }
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute Product product){
        productRepository.saveAndFlush(product);
        return "redirect:/admin/products";
    }
    @RequestMapping(value = "/products/update/{id}", method = RequestMethod.POST)
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute ProductDescription product){
        productService.updateProduct(id,product);
        return "redirect:/admin/products";
    }
}
