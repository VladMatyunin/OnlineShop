package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.model.ProductItem;
import ru.kpfu.itis.model.Storage;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.service.repository.ProductItemRepo;
import ru.kpfu.itis.service.repository.ProductRepository;
import ru.kpfu.itis.service.repository.StorageRepository;

/**
 * Created by vladislav on 07.05.17.
 */
@Controller
@RequestMapping(value = "/admin/storage")
public class AdminStorageController {
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductItemRepo productItemRepo;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loadAllStorage(ModelMap modelMap) {
        modelMap.addAttribute("storage", storageRepository.findAll());
        return "admin/storage";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createStorage(@ModelAttribute Storage storage) {
        storageRepository.saveAndFlush(storage);
        return "redirect:/admin/storage";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String loadOneStorage(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("storage", storageRepository.findOne(id));
        return "admin/oneStorage";
    }

    @RequestMapping(value = "/{id}/add_product", method = RequestMethod.POST)
    public String addProduct(@PathVariable("id") Long id, @RequestParam("product_id") Long productId,
                             @RequestParam("product_num") int num) {
        ProductItem productItem = new ProductItem();
        productItem.setNumber(num);
        productItem.setProduct(productRepository.findOne(productId));
        storageService.addProduct(id, productItem);
        return "redirect:/admin/storage/"+id;
    }
    @RequestMapping(value = "/{id}/delete_product/{product_id}")
    public String deleteProduct(@PathVariable("id") Long storageId, @PathVariable("product_id") Long productId){
        storageService.deleteProduct(storageId, productId);
        return "redirect:/admin/storage/"+storageId;
    }
    @ResponseBody
    @RequestMapping(value = "/save/{it_id}", method = RequestMethod.POST)
    public void saveProductItem(@PathVariable("it_id") Long itemId, @RequestParam("num") int count){
        ProductItem productItem = productItemRepo.findOne(itemId);
        productItem.setNumber(count);
        productItemRepo.saveAndFlush(productItem);
    }
}
