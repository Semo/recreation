package com.y4d3.bootstrap;

import com.y4d3.domain.*;
import com.y4d3.domain.roles.Role;
import com.y4d3.enums.OrderStatus;
import com.y4d3.services.ProductService;
import com.y4d3.services.RoleService;
import com.y4d3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by semo on 21.12.16.
 */
@Component
public class BootupCustomers implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();
        loadRoles();
        assignUsersToDefaultRole();

    }

    private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("CUSTOMER")) {
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);
    }

    private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setOrderStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
            });
        });
    }

    private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartItem cartDetail = new CartItem();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartItem(cartDetail);
            userService.saveOrUpdate(user);
        });
    }

    public void loadUsersAndCustomers() {
        User user1 = new User();
        user1.setUsername("mweston");
        user1.setPassword("password");

        Customer customer1 = new Customer();
        customer1.setFirstname("Micheal");
        customer1.setLastname("Weston");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("1 Main St");
        customer1.getBillingAddress().setCity("Miami");
        customer1.getBillingAddress().setState("Florida");
        customer1.getBillingAddress().setZip("33101");
        customer1.setEmail("micheal@burnnotice.com");
        customer1.setPhonenumber("305.333.0101");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("fglenanne");
        user2.setPassword("password");

        Customer customer2 = new Customer();
        customer2.setFirstname("Fiona");
        customer2.setLastname("Glenanne");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLine1("1 Key Biscane Ave");
        customer2.getBillingAddress().setCity("Miami");
        customer2.getBillingAddress().setState("Florida");
        customer2.getBillingAddress().setZip("33101");
        customer2.setEmail("fiona@burnnotice.com");
        customer2.setPhonenumber("305.323.0233");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("saxe");
        user3.setPassword("password");
        Customer customer3 = new Customer();
        customer3.setFirstname("Sam");
        customer3.setLastname("Axe");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLine1("1 Little Cuba Road");
        customer3.getBillingAddress().setCity("Miami");
        customer3.getBillingAddress().setState("Florida");
        customer3.getBillingAddress().setZip("33101");
        customer3.setEmail("sam@burnnotice.com");
        customer3.setPhonenumber("305.426.9832");

        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);
    }

    public void loadProducts() {

        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://example.com/product2");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://example.com/product3");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://example.com/product4");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("25.99"));
        product5.setImageUrl("http://example.com/product5");
        productService.saveOrUpdate(product5);

    }


//    private CustomerService customerService;
//
//    private UserService userService;
//    private RoleService roleService;
//
//    private ProductService productService;
//
//
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @Autowired
//    public void setRoleService(RoleService roleService) {
//        this.roleService = roleService;
//    }
//
//
//    @Autowired
//    public void setCustomerService(CustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        loadProducts();
//        loadUsersAndCustomers();
//        loadCarts();
//        loadOrderHistory();
//        loadRoles();
//        assignUsersToDefaultRole();
//    }
//
//    private void assignUsersToDefaultRole() {
//        List<Role> roles = (List<Role>) roleService.listAll();
//        List<User> users = (List<User>) userService.listAll();
//
//        roles.forEach(role -> {
//            if (role.getRole().equalsIgnoreCase("CUSTOMER")) {
//                users.forEach(user -> {
//                    user.addRole(role);
//                    userService.saveOrUpdate(user);
//                });
//            }
//        });
//    }
//
//    private void loadRoles() {
//        Role role = new Role();
//        role.setRole("CUSTOMER");
//        roleService.saveOrUpdate(role);
//    }
//
//    private void loadOrderHistory() {
//        List<User> users = (List<User>) userService.listAll();
//        List<Product> products = (List<Product>) productService.listAll();
//
//        users.forEach(user -> {
//            Order order = new Order();
//            order.setCustomer(user.getCustomer());
//            order.setOrderStatus(OrderStatus.SHIPPED);
//
//            products.forEach(product -> {
//                OrderDetail orderDetail = new OrderDetail();
//                orderDetail.setProduct(product);
//                orderDetail.setQuantity(1);
//                order.addToOrderDetails(orderDetail);
//            });
//        });
//    }
//
//    private void loadCarts() {
//        List<User> users = (List<User>) userService.listAll();
//        List<Product> products = (List<Product>) productService.listAll();
//
//        users.forEach(user -> {
//            user.setCart(new Cart());
//            CartItem cartItem = new CartItem();
//            cartItem.setProduct(products.get(0));
//            cartItem.setQuantity(2);
//            user.getCart().addCartItem(cartItem);
//            userService.saveOrUpdate(user);
//        });
//    }
//
//
//    private void loadUsersAndCustomers() {
//        User user1 = new User();
//        user1.setUsername("pvenkman");
//        user1.setPassword("password");
//
//        Customer c1 = new Customer();
//        c1.setId(1);
//        c1.setFirstname("Peter");
//        c1.setLastname("Venkman");
//        c1.setBillingAddress(new Address());
//        c1.getBillingAddress().setAddressLine1("Einestrasse 1");
//        c1.getBillingAddress().setAddressLine2("Einestrasse 12");
//        c1.getBillingAddress().setCity("New York");
//        c1.getBillingAddress().setZip("NY 9000");
//        c1.getBillingAddress().setState("New York");
//        c1.setShippingAddress(new Address());
//        c1.getShippingAddress().setAddressLine1("Einestrasse 3");
//        c1.getShippingAddress().setAddressLine2("Einestrasse 13");
//        c1.getShippingAddress().setCity("Philadelphia");
//        c1.getShippingAddress().setState("New York");
//        c1.getShippingAddress().setZip("9500 PHI");
//        c1.setEmail("mail1@example.org");
//        c1.setPhonenumber("13251");
//        c1.setUser(user1);
//        customerService.saveOrUpdate(c1);
//
//        User user2 = new User();
//        user2.setUsername("rstantz");
//        user2.setPassword("password");
//
//        Customer c2 = new Customer();
//        c2.setId(2);
//        c2.setFirstname("Ray");
//        c2.setLastname("Stantz");
//        c2.setBillingAddress(new Address());
//        c2.getBillingAddress().setAddressLine1("Einestrasse 1");
//        c2.getBillingAddress().setAddressLine2("Einestrasse 12");
//        c2.getBillingAddress().setCity("2132");
//        c2.getBillingAddress().setZip("NY 9000");
//        c2.getBillingAddress().setState("New York");
//        c2.setShippingAddress(new Address());
//        c2.getShippingAddress().setAddressLine1("Zweitestrasse 4");
//        c2.getShippingAddress().setAddressLine2("Zweitestrasse 14");
//        c2.getShippingAddress().setCity("Philadelhia");
//        c2.getShippingAddress().setState("New York");
//        c2.getShippingAddress().setZip("9500 PHI");
//        c2.setEmail("mail2@example.org");
//        c2.setPhonenumber("13251");
//        c2.setUser(user2);
//        customerService.saveOrUpdate(c2);
//
//        User user3 = new User();
//        user3.setUsername("espengler");
//        user3.setPassword("password");
//
//        Customer c3 = new Customer();
//        c3.setId(3);
//        c3.setFirstname("Egon");
//        c3.setLastname("Spengler");
//        c3.setBillingAddress(new Address());
//        c3.getBillingAddress().setAddressLine1("Einestrasse 1");
//        c3.getBillingAddress().setAddressLine2("Einelstrasse 12");
//        c3.getBillingAddress().setCity("2132");
//        c3.getBillingAddress().setZip("NY 9000");
//        c3.getBillingAddress().setState("New York");
//        c3.setShippingAddress(new Address());
//        c3.getShippingAddress().setAddressLine1("Drittestrasse 5");
//        c3.getShippingAddress().setAddressLine2("Drittestrasse 15");
//        c3.getShippingAddress().setCity("Philadelphia");
//        c3.getShippingAddress().setState("New York");
//        c3.getShippingAddress().setZip("9500 PHI");
//        c3.setEmail("mail3@example.org");
//        c3.setPhonenumber("13251");
//        c3.setUser(user3);
//        customerService.saveOrUpdate(c3);
//
//        User user4 = new User();
//        user4.setUsername("wzeddemore");
//        user4.setPassword("password");
//
//        Customer c4 = new Customer();
//        c4.setId(4);
//        c4.setFirstname("Winston");
//        c4.setLastname("Zeddemore");
//        c4.setBillingAddress(new Address());
//        c4.getBillingAddress().setAddressLine1("Einestrasse 1");
//        c4.getBillingAddress().setAddressLine2("Einestrasse 12");
//        c4.getBillingAddress().setCity("2132");
//        c4.getBillingAddress().setState("New York");
//        c4.getBillingAddress().setZip("NY 9000");
//        c4.setEmail("mail1@example.org");
//        c4.setPhonenumber("13251");
//        c4.setShippingAddress(new Address());
//        c4.getShippingAddress().setAddressLine1("Viertestrasse 6");
//        c4.getShippingAddress().setAddressLine2("Viertestrasse 16");
//        c4.getShippingAddress().setCity("Philadelphia");
//        c4.getShippingAddress().setState("New York");
//        c4.getShippingAddress().setZip("9500 PHI");
//        c4.setUser(user4);
//        customerService.saveOrUpdate(c4);
//    }
//
//    private void loadProducts() {
//        Product p1 = new Product();
//        p1.setId(1);
//        p1.setDescription("This is product 1.");
//        p1.setPrice(new BigDecimal(1.99));
//        p1.setImageUrl("/images/1");
//        productService.saveOrUpdate(p1);
//
//
//        Product p2 = new Product();
//        p2.setId(2);
//        p2.setDescription("This is product 2.");
//        p2.setPrice(new BigDecimal(199.99));
//        p2.setImageUrl("/images/2");
//        productService.saveOrUpdate(p2);
//
//
//        Product p3 = new Product();
//        p3.setId(3);
//        p3.setDescription("This is product 3.");
//        p3.setPrice(new BigDecimal(22.11));
//        p3.setImageUrl("/images/3");
//        productService.saveOrUpdate(p3);
//
//        Product p4 = new Product();
//        p4.setId(4);
//        p4.setDescription("This is product 4.");
//        p4.setPrice(new BigDecimal(21.95));
//        p4.setImageUrl("/images/4");
//        productService.saveOrUpdate(p4);
//
//        Product p5 = new Product();
//        p5.setId(5);
//        p5.setDescription("This is product 5.");
//        p5.setPrice(new BigDecimal(24.95));
//        p5.setImageUrl("/images/5");
//        productService.saveOrUpdate(p5);
//
//    }


}
