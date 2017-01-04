package com.y4d3.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by semo on 27.12.16.
 */
@Embeddable
public class Address {

    @Column(insertable = false, updatable = false)
    private String addressLine1;

    @Column(insertable = false, updatable = false)
    private String addressLine2;

    @Column(insertable = false, updatable = false)
    private String city;

    @Column(insertable = false, updatable = false)
    private String state;

    @Column(insertable = false, updatable = false)
    private String zip;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
