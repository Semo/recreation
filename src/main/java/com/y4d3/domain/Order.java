package com.y4d3.domain;

import com.y4d3.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by semo on 02.01.17.
 */
@Entity
@Table(name = "ORDER_HEADER")
public class Order extends ADomainObject {

    @OneToOne
    private Customer customer;

    @Embedded
    private Address shipToAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

    private OrderStatus orderStatus;
    private Date dateShipped;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(Address shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public void addToOrderDetails(OrderDetail orderDetail) {
        orderDetail.setOrder(this);
        orderDetails.add(orderDetail);
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        orderDetail.setOrder(null);
        orderDetails.remove(orderDetail);
    }

}
