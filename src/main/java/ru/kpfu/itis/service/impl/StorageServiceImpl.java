package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.ProductItem;
import ru.kpfu.itis.model.Storage;
import ru.kpfu.itis.model.additional.TransactionInform;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.service.repository.StorageRepository;

import java.util.List;
import java.util.Objects;

/**
 * Created by vladislav on 04.05.17.
 */
@Service
public class StorageServiceImpl implements StorageService{
    @Autowired
    StorageRepository storageRepository;
    @Override
    public TransactionInform update(Long storageId, ProductItem productItem) {
        Storage storage = storageRepository.findOne(storageId);
        storage.getProductItems().add(productItem);
        return new TransactionInform(true,"OK");
    }

    @Override
    public int getProductCount(Long storageId, Long productId) {
        List<ProductItem> productItems = storageRepository.findOne(storageId).getProductItems();
        for (ProductItem p : productItems) {
            if (Objects.equals(p.getId(), productId)) return p.getNumber();
        }
        return 0;
    }

    @Override
    public int getTotalProductCount(Long productId) {
        List<Storage> allStorages = storageRepository.findAll();
        int count = 0;
        for (Storage s: allStorages) {
            count+=getProductCount(s.getId(),productId);
        }
        return count;
    }

    @Override
    public void addProduct(Long storageId, ProductItem productItem) {
        Storage storage = storageRepository.findOne(storageId);
        storage.getProductItems().add(productItem);
        storageRepository.saveAndFlush(storage);
    }

    @Override
    public void deleteProduct(Long storageId, Long productId) {
        Storage storage = storageRepository.findOne(storageId);
        for (ProductItem productItem: storage.getProductItems()){
            if (productItem.getProduct().getId().equals(productId)){
                storage.getProductItems().remove(productItem);
            }
        }
        storageRepository.saveAndFlush(storage);
    }
}
