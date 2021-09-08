package com.telran.phonebookapi.entitytest;

import com.telran.phonebookapi.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityTest {
    @Test
    public void createContactEntityTest() {

        Contact contact = new Contact("marina", "mitunevich", 30, true, Group.HOME);

        String name = "marina";
        assertEquals(name, contact.getFirstName());

        int age = 30;
        assertEquals(age, contact.getAge());

    }

    @Test
    public void additionEmailToContactTest() {

        Contact contact = new Contact();

        Email email = new Email("marina@mail.ru", true, contact);
        contact.addEmail(email);

        assertEquals(Arrays.asList(email), contact.getEmails());

    }

    @Test
    public void additionPhoneToContactTest() {

        Contact contact = new Contact();

        Phone phone = new Phone("Ger", "067896546", false, contact);
        contact.addPhone(phone);

        assertEquals(Arrays.asList(phone), contact.getPhones());

    }

    @Test
    public void additionAddressToContactTest() {

        Contact contact = new Contact();

        Address address = new Address("Germany", "Hamburg", "Ludwig 13", "1957", false, contact);
        contact.addAddress(address);

        assertEquals(Arrays.asList(address), contact.getAddresses());

    }

    @Test
    public void changeFieldFirstNameOfContactTest() {

        String name = "marina";
        Contact contact = new Contact(name, "mitunevich", 30, true, Group.HOME);

        assertEquals(name, contact.getFirstName());

        String newFirstName = "vasya";
        contact.setFirstName(newFirstName);

        assertEquals(newFirstName, contact.getFirstName());

    }

    @Test
    public void changeFieldLastNameOfContactTest() {

        String lastName = "mitunevich";
        Contact contact = new Contact("marina", lastName, 30, true, Group.HOME);

        assertEquals(lastName, contact.getLastName());

        String newLastName = "bergner";
        contact.setLastName(newLastName);

        assertEquals(newLastName, contact.getLastName());

    }

    @Test
    public void changeFieldAgeContactTest() {

        int age = 20;
        Contact contact = new Contact("marina", "bergner", age, true, Group.HOME);

        assertEquals(age, contact.getAge());

        int newAge = 10;
        contact.setAge(newAge);

        assertEquals(newAge, contact.getAge());

    }

    @Test
    public void changeFieldGroupOfContactTest() {


        Contact contact = new Contact("marina", "berbner", 30, true, Group.HOME);

        assertEquals(Group.HOME, contact.getGroup());

        Group newGroup = Group.OTHER;
        contact.setGroup(newGroup);

        assertEquals(newGroup, contact.getGroup());

    }

    @Test
    public void changeFieldIsFavouriteContactTest() {

        boolean favourite = true;
        Contact contact = new Contact("marina", "bergner", 20, favourite, Group.HOME);

        assertEquals(favourite, contact.isFavourite());

        boolean newFavourite = false;
        contact.setFavourite(newFavourite);

        assertEquals(newFavourite, contact.isFavourite());

    }


    @Test
    public void createEmailEntityTest() {

        Contact contact = new Contact("marina", "mitunevich", 30, true, Group.HOME);

        Email email = new Email("marina@mail.ru", true, contact);

        String myEmail = "marina@mail.ru";
        assertEquals(myEmail, email.getEmail());

    }

    @Test
    public void createPhoneEntityTest() {

        Contact contact = new Contact("marina", "mitunevich", 30, true, Group.HOME);

        Phone phone = new Phone("Ger", "067896546", false, contact);

        assertEquals(contact, phone.getContact());

    }

    @Test
    public void createAddressTest() {

        Contact contact = new Contact("marina", "mitunevich", 30, true, Group.HOME);

        Address address = new Address("Germany", "Hamburg", "Ludwig 13", "1957", false, contact);

        assertEquals(contact, address.getContact());

    }

}
