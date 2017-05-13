package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.model.*;
import ru.kpfu.itis.model.form.ProductForm;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.helper.AdminHelper;
import ru.kpfu.itis.service.repository.*;

import javax.validation.Valid;

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
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private AdminHelper adminHelper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductDescriptionRepo productDescriptionRepo;

    @RequestMapping(value = "/users")
    public String loadUsersPage(ModelMap modelMap){
        modelMap.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }
    @ResponseBody
    @RequestMapping(value = "/users/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userRepository.delete(id);
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
        modelMap.addAttribute("productForm", new ProductForm());
        modelMap.addAttribute("allbrands", adminHelper.brandsToString(brandRepository.findAll()));
        modelMap.addAttribute("allcats", adminHelper.categoriesToString(categoryRepository.findAll()));
        return "admin/products";
    }
    //TODO: remove to service
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute @Valid ProductForm productForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "admin/products";
        Category selectedCategory = categoryRepository.findByName(productForm.getCategory());
        Brand selectedBrand = brandRepository.findByName(productForm.getBrand());
        productRepository.saveAndFlush(productForm.getProduct(productDescriptionRepo.saveAndFlush(productForm.get()),
                selectedCategory,selectedBrand));
        return "redirect:/admin/products";
    }
    @RequestMapping(value = "/products/update/{id}", method = RequestMethod.POST)
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute ProductDescription product){
        productService.updateProduct(id,product);
        return "redirect:/admin/products";
    }
    @ResponseBody
    @RequestMapping(value = "/products/update/{id}", method = RequestMethod.GET)
    public ModelAndView loadModalPage(@PathVariable("id") Long productId){
        ModelAndView modelAndView = new ModelAndView("admin/productModal");
        modelAndView.addObject("product", productRepository.findOne(productId));
        return modelAndView;
    }
}
