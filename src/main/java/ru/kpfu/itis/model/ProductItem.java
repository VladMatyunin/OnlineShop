package ru.kpfu.itis.model;

import javax.persistence.*;

/**
 * Created by vladislav on 04.05.17.
 */
@Entity
public class ProductItem {
    public ProductItem(){}

    public ProductItem(Product product, int number) {
        this.product = product;
        this.number = number;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Product product;
    private int number;
}
