package com.telran.phonebookapi.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private String firstName;

    @Setter
    private String lastName;

    @Setter
    private int age;

    @Setter
    private boolean isFavourite;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_group")
    Group group;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    List<Email> emails = new ArrayList<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    List<Phone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    List<Address> addresses = new ArrayList<>();

    public Contact(@NonNull String firstName, @NonNull String lastName, int age, boolean isFavourite, @NonNull Group group) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isFavourite = isFavourite;
        this.group = group;

    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void addEmail(Email email) {
        this.emails.add(email);
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id && age == contact.age && isFavourite == contact.isFavourite && Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName) && group == contact.group && Objects.equals(emails, contact.emails) && Objects.equals(phones, contact.phones) && Objects.equals(addresses, contact.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, isFavourite, group, emails, phones, addresses);
    }
}
