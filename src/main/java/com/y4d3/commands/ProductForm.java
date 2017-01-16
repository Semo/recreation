package com.y4d3.commands;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by gandalf on 16.01.2017.
 */
public class ProductForm {

    private Integer id;
    private Integer version;

    @NotEmpty
    @Size(min = 20, max = 255)
    private String description;

    @NotNull
    @Min(0)
    @Max(10000)
    private BigDecimal price;

    @NotEmpty
    @URL
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
