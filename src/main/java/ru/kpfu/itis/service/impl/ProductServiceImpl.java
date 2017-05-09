package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Brand;
import ru.kpfu.itis.model.Category;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.ProductDescription;
import ru.kpfu.itis.model.additional.TransactionInform;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.repository.BrandRepository;
import ru.kpfu.itis.service.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladislav on 04.05.17.
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public TransactionInform createProduct(Product product) {
        productRepository.saveAndFlush(product);
        return new TransactionInform(true,"OK");
    }

    @Override
    public TransactionInform updateProduct(Long productId, ProductDescription description) {
        Product product = productRepository.findOne(productId);
        product.setDescription(description);
        productRepository.saveAndFlush(product);
        return new TransactionInform(true,"OK");
    }

    @Override
    public List<Product> filter(Category category, int minPrice, int maxPrice, long[] brandsId) {
        List<Brand> brands = new ArrayList<>();
        for (long aBrandsId : brandsId) {
            brands.add(brandRepository.findOne(aBrandsId));
        }
        if (brands.size()>0)
        return productRepository.findAllByCategoryAndCostBetweenAndBrandIn(category,minPrice,maxPrice,brands);
        else return productRepository.findAllByCategoryAndCostBetween(category,minPrice,maxPrice);
    }
}
