package com.redesaudeal.app.redesaude.Domain;

public class Address {

    private String city;
    private String neighborhood;
    private String street;
    private String complement;

    public Address(String city, String neighborhood, String street, String complement) {
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.complement = complement;
    }
}
