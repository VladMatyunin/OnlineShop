package ru.kpfu.itis.service;

import ru.kpfu.itis.model.Category;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.ProductDescription;
import ru.kpfu.itis.model.additional.TransactionInform;

import java.util.List;

/**
 * Created by vladislav on 04.05.17.
 */
public interface ProductService {
    TransactionInform createProduct(Product product);
    TransactionInform updateProduct(Long productId, ProductDescription description);
    List<Product> filter(Category category, int minPrice, int maxPrice, long[] brandsId);
}
