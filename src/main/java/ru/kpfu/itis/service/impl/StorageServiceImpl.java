package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.ProductItem;
import ru.kpfu.itis.model.Storage;
import ru.kpfu.itis.model.additional.Cart;
import ru.kpfu.itis.model.additional.TransactionInform;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.service.repository.ProductItemRepo;
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
    @Autowired
    private ProductItemRepo productItemRepo;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void addProduct(Long storageId, ProductItem productItem) {
        Storage storage = storageRepository.findOne(storageId);
        storage.getProductItems().add(productItemRepo.save(productItem));
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
    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    public void doOrder(Cart cart) {
        //TODO: optimize
        List<Storage> storages = storageRepository.findAll();
        for (Storage storage: storages){
            for (ProductItem productItem: storage.getProductItems()){
                for (ProductItem cartProductItem: cart.getProductItems()){
                    if (productItem.getProduct().getId().equals(cartProductItem.getProduct().getId())){
                        if (productItem.getNumber()>=cartProductItem.getNumber()){
                            cartProductItem.setNumber(0);
                            productItem.setNumber(productItem.getNumber()-cartProductItem.getNumber());
                            break;
                        }
                        else {
                            productItem.setNumber(0);
                            cartProductItem.setNumber(cartProductItem.getNumber()-productItem.getNumber());
                        }
                    }
                }
            }
        }
    }
}
