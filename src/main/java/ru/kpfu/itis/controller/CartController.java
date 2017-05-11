package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.model.additional.Cart;
import ru.kpfu.itis.service.StorageService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vladislav on 04.05.17.
 */
@Controller
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private StorageService storageService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loadCart(ModelMap modelMap){
        modelMap.addAttribute("cart", httpServletRequest.getSession().getAttribute("cart"));
        return "cart";
    }
    @ResponseBody
    @RequestMapping(value = "/count")
    public boolean setCount(@RequestParam("productId") Long productId, int count){
        Cart cart = (Cart) httpServletRequest.getSession().getAttribute("cart");
        int maxCount = storageService.getTotalProductCount(productId);
        int cartCount = cart.getProductCount(productId);
        if (cartCount+count>maxCount) return false;
        if (cartCount+count<=0) return false;
        cart.setProductCount(productId, cartCount+count);
        return true;
    }
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public void deleteProductFromCart(@RequestParam("prId") Long productItemId){
        Cart cart = (Cart) httpServletRequest.getSession().getAttribute("cart");
        cart.deleteProduct(productItemId);
    }
}
