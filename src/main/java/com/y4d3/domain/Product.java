package com.y4d3.domain;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by root on 15.12.16.
 */
@Entity
public class Product extends ADomainObject {

    private String description;
    private BigDecimal price;
    private String imageUrl;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
