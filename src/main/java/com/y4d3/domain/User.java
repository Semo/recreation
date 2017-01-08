package com.y4d3.domain;

import com.y4d3.domain.roles.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by semo on 27.12.16.
 */
@Entity
public class User extends ADomainObject {

    private String username;

    @Transient
    private String password;

    private String encryptedPassword;
    private Boolean isActive = true;

    @OneToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST})
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    // ~ defaults to @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id"),
    //     inverseJoinColumns = @joinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();


//    private Boolean isEncrytionActive = true;

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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
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
        return isActive;
    }

    public void setEncrytionActive(Boolean encrytionActive) {

        isActive = encrytionActive;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if (!this.roles.contains(role)) {
            this.roles.add(role);
        }

        if (!role.getUsers().contains(this)) {
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

}
