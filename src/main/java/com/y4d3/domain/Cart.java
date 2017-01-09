package com.y4d3.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by semo on 27.12.16.
 */
@Entity
public class Cart extends ADomainObject {

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<CartItem>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    public void removeCartItem(CartItem cartItem) {
        cartItem.setCart(null);
        cartItems.remove(cartItem);
    }
}
