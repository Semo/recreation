package com.y4d3.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Created by semo on 27.12.16.
 */
@Entity
public class User extends ADomainObject {

    private String username;

    @Transient
    private String password;

    private String encryptedPassword;

    private String isActive;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    private Boolean isEncrytionActive = true;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        customer.setUser(this);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEncrytionActive() {
        return isEncrytionActive;
    }

    public void setEncrytionActive(Boolean encrytionActive) {
        isEncrytionActive = encrytionActive;
    }

}
