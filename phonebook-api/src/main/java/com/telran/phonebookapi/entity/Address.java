package com.telran.phonebookapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "index")
    private String index;

    @Column(name = "is_favorite")
    private boolean isFavorite;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    Contact contact;

    public Address() {}

    public Address(String country, String city, String address, String index, boolean isFavorite, Contact contact) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.index = index;
        this.isFavorite = isFavorite;
        this.contact = contact;
    }


    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getIndex() {
        return index;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public Contact getContact() {
        return contact;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return id == address1.id && isFavorite == address1.isFavorite && Objects.equals(country, address1.country) && Objects.equals(city, address1.city) && Objects.equals(address, address1.address) && Objects.equals(index, address1.index) && Objects.equals(contact, address1.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, address, index, isFavorite, contact);
    }
}
