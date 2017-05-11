package ru.kpfu.itis.model.additional;

import org.springframework.context.annotation.Scope;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.ProductItem;
import ru.kpfu.itis.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladislav on 07.05.17.
 */
@Scope("session")
public class Cart implements Serializable {
    private User user;
    private List<ProductItem> productItems;
    public Cart(){
        productItems = new ArrayList<>();
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void addProduct(Product product){
        for (ProductItem pr:getProductItems()) {
            if (pr.getProduct().getId().equals(product.getId())){
                return;
            }
        }
        getProductItems().add(new ProductItem(product,1));
    }
    public int getProductCount(Long productId){
        for (ProductItem pr:getProductItems()) {
            if (pr.getProduct().getId().equals(productId)){
                return pr.getNumber();
            }
        }
        return 0;
    }
    public void setProductCount(Long productId, int num){
        for (ProductItem pr:getProductItems()) {
            if (pr.getProduct().getId().equals(productId)){
                pr.setNumber(num);
            }
        }
    }
    public int getTotalCount(){
        return productItems.size();
    }
    //TODO: optimize
    public void deleteProduct(Long id){
        for (ProductItem pr:getProductItems()) {
            if (pr.getProduct().getId().equals(id)){
                getProductItems().remove(pr);
            }
        }
    }
}
