package ru.kpfu.itis.model.form;

import ru.kpfu.itis.model.Brand;
import ru.kpfu.itis.model.Category;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.ProductDescription;

/**
 * Created by vladislav on 12.05.17.
 */
public class ProductForm {
    private String name;
    private String description;
    private int cost;
    private String brand;
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public ProductDescription get(){
        return new ProductDescription(this.name,this.description,this.cost);
    }
    public Product getProduct(ProductDescription productDescription, Category category, Brand brand){
        Product product = new Product();
        product.setCost(cost);
        product.setDescription(productDescription);
        product.setCategory(category);
        product.setBrand(brand);
        return product;
    }
}
