package com.y4d3.services;

/**
 * Created by semo on 02.01.17.
 */


import com.y4d3.config.DiexampleConfigIT;
import com.y4d3.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;

/**
 * Created by jt on 12/14/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DiexampleConfigIT.class)
@ActiveProfiles("jpadao")
public class UserSrvcJpaDaoImplTest {

    private UserService userService;
    private ProductService productService;
//    private CartItem cartItemOne;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testSaveOfUser() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getEncryptedPassword() != null;

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());

    }

    @Test
    public void testSaveOfUserWithCustomer() throws Exception {

        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        Customer customer = new Customer();
        customer.setFirstname("Chevy");
        customer.setLastname("Chase");

        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        System.out.println(savedUser.getCustomer());
        assert savedUser.getCustomer() != null;
        assert savedUser.getCustomer().getId() != null;

    }

    @Test
    public void testAddCartToUser() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCart() != null;
        assert savedUser.getCart().getId() != null;
    }

    @Test
    public void testAddCartToUserWithCartDetails() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartItem cartItemOne = new CartItem();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartItem(cartItemOne);

        CartItem cartItemTwo = new CartItem();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartItem(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCart() != null;
        assert savedUser.getCart().getId() != null;
        assert savedUser.getCart().getCartItems().size() == 2;
    }

    @Test
    public void testAddAndRemoveCartToUserWithCartDetails() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartItem cartItemOne = new CartItem();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartItem(cartItemOne);

        CartItem cartItemTwo = new CartItem();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartItem(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getCart().getCartItems().size() == 2;
        savedUser.getCart().removeCartItem(savedUser.getCart().getCartItems().get(0));

        userService.saveOrUpdate(savedUser);
        assert savedUser.getCart().getCartItems().size() == 1;
    }
}