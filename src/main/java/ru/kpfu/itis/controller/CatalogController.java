package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.model.Brand;
import ru.kpfu.itis.model.Category;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.ProductItem;
import ru.kpfu.itis.model.additional.Cart;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.service.repository.BrandRepository;
import ru.kpfu.itis.service.repository.CategoryRepository;
import ru.kpfu.itis.service.repository.ProductRepository;
import ru.kpfu.itis.service.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladislav on 04.05.17.
 */
@Controller
@RequestMapping(value = "/catalog")
public class CatalogController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProductService productService;
    private int minPrice = 0;
    private int maxPrice = 50000;
    private long[] brandsFilter = new long[0];
    private List<Category> categories = new ArrayList<>();
    private List<Brand> brands = new ArrayList<>();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String loadCatalog(@PathVariable("id") Long id, ModelMap modelMap) {
        List<ProductItem> catalog = new ArrayList<>();
        Category currentCategory = categoryRepository.findOne(id);
        List<Product> allproducts = productService.filter(categoryRepository.findOne(id),minPrice,maxPrice,brandsFilter);
        for (Product product : allproducts) {
            catalog.add(new ProductItem(product, storageService.getTotalProductCount(product.getId())));
        }
        modelMap.addAttribute("brandIds", brandsFilter);
        modelMap.addAttribute("maxPrice", maxPrice);
        modelMap.addAttribute("minPrice", minPrice);
        modelMap.addAttribute("currentCategory", currentCategory);
        modelMap.addAttribute("catalog", catalog);
        modelMap.addAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("brands", brandRepository.findAll());
        return "main";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String loadFilters(@RequestParam(value = "minCost") String minCost,
                              @RequestParam(value = "maxCost") String maxCost,
                              @RequestParam("currentCategory") Long catId, ModelMap modelMap) {
        minPrice = Integer.parseInt(minCost.replaceAll("[^0-9]", ""));
        maxPrice = Integer.parseInt(maxCost.replaceAll("[^0-9]", ""));
        return "redirect:/catalog/" + catId;
    }

    @RequestMapping(value = "/search_brands", method = RequestMethod.POST)
    public String addBrands(@RequestParam(value = "currentCategory", required = false) Long catId,
                            @RequestParam(value = "brands", required = false) long[] ids, ModelMap modelMap) {
        if (ids.length>0){
            brandsFilter = ids;
        }
        return "redirect:/catalog/" + catId;
    }

    @ResponseBody
    @RequestMapping(value = "/add_product", method = RequestMethod.POST)
    public int addProduct(@RequestParam("productId") Long productId) {
        HttpSession session = httpServletRequest.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.addProduct(productRepository.findOne(productId));
        session.setAttribute("cart", cart);
        return cart.getProductItems().size();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/details")
    public ModelAndView loadProductDetails(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("product", productRepository.findOne(id));
        return modelAndView;
    }
}
