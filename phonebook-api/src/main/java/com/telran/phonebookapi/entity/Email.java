package com.telran.phonebookapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "is_favorite")
    private boolean isFavorite;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    Contact contact;

    public Email() {
    }

    public Email(String email, boolean isFavorite, Contact contact) {
        this.email = email;
        this.isFavorite = isFavorite;
        this.contact = contact;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public Contact getContact() {
        return contact;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Email email1 = (Email) o;
        return id == email1.id && isFavorite == email1.isFavorite && Objects.equals(email, email1.email) && Objects.equals(contact, email1.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, isFavorite, contact);
    }
}
