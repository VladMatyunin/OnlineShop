package ru.kpfu.itis.model;

import javax.persistence.*;

/**
 * Created by vladislav on 04.05.17.
 */
@Entity
@Table(name = "p_desc")
public class ProductDescription {
    public ProductDescription(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private int cost;

    public ProductDescription(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
