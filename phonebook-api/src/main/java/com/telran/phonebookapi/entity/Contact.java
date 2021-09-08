package com.telran.phonebookapi.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "is_favorite")
    private boolean isFavourite;

    @Enumerated(EnumType.STRING)
    @Column(name = "group")
    Group group;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    List<Email> emails = new ArrayList<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    List<Phone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    List<Address> addresses = new ArrayList<>();

    public Contact() {
    }

    public Contact(String firstName, String lastName, int age, boolean isFavourite, Group group) {

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


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public Group getGroup() {
        return group;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", isFavourite=" + isFavourite +
                ", group=" + group +
                ", emails=" + emails +
                ", phones=" + phones +
                ", addresses=" + addresses +
                '}';
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
