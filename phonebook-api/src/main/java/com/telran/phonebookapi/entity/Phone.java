package com.telran.phonebookapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String countryCode;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "is_favorite")
    private boolean isFavorite;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    Contact contact;

    public Phone() {}

    public Phone(String countryCode, String telephoneNumber, boolean isFavorite, Contact contact) {
        this.countryCode = countryCode;
        this.telephoneNumber = telephoneNumber;
        this.isFavorite = isFavorite;
        this.contact = contact;
    }

    public long getId() {
        return id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public Contact getContact() {
        return contact;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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
        Phone phone = (Phone) o;
        return id == phone.id && isFavorite == phone.isFavorite && Objects.equals(countryCode, phone.countryCode) && Objects.equals(telephoneNumber, phone.telephoneNumber) && Objects.equals(contact, phone.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryCode, telephoneNumber, isFavorite, contact);
    }
}
