package ru.kpfu.itis.service;

import ru.kpfu.itis.model.ProductItem;
import ru.kpfu.itis.model.additional.Cart;
import ru.kpfu.itis.model.additional.TransactionInform;

/**
 * Created by vladislav on 04.05.17.
 */
public interface StorageService {
    TransactionInform update(Long storageId, ProductItem productItem);
    int getProductCount(Long storageId, Long productId);
    int getTotalProductCount(Long productId);
    void addProduct(Long storageId, ProductItem productItem);
    void deleteProduct(Long storageId, Long productId);
    void doOrder(Cart cart);
}
