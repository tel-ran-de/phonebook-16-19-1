package com.telran.phonebookapi.repotest;

import com.telran.phonebookapi.entity.*;
import com.telran.phonebookapi.repo.IContactRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class IContactRepoTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    IContactRepo iContactRepo;

    @Test
    public void addContact() {

        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);

        entityManager.persist(contact);

        entityManager.flush();
        entityManager.clear();

        List<Contact> contacts = iContactRepo.findAll();

        assertEquals(1, contacts.size());
    }

    @Test
    public void editContact() {

        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);

        Phone phone1 = new Phone("+49", "12345", true, contact);
        Phone phone2 = new Phone("+35", "123456", true, contact);

        Email email1 = new Email("bolenderm91@gmail.com", true, contact);
        Email email2 = new Email("stormblade91@gmail.com", true, contact);

        Address address1 = new Address(
                "Germany",
                "Berlin",
                "Südekumzeile",
                "13591",
                true, contact);
        Address address2 = new Address(
                "Russia",
                "Moscow",
                "Lenina",
                "169300",
                false, contact);

        contact.addPhone(phone1);
        contact.addPhone(phone2);
        contact.addEmail(email1);
        contact.addEmail(email2);
        contact.addAddress(address1);
        contact.addAddress(address2);

        entityManager.persist(contact);
        entityManager.persist(phone1);
        entityManager.persist(phone2);
        entityManager.persist(email1);
        entityManager.persist(email2);
        entityManager.persist(address1);
        entityManager.persist(address2);

        entityManager.flush();
        entityManager.clear();

        Contact addedContact = iContactRepo.findAll().get(0);
        assertEquals(contact.getId(), addedContact.getId());

        Address addedAddress1 = contact.getAddresses().get(0);
        assertEquals(address1.getAddress(), addedAddress1.getAddress());
        assertEquals(address1.getCity(), addedAddress1.getCity());
        assertEquals(address1.getCountry(), addedAddress1.getCountry());
        assertEquals(address1.getContact(), addedAddress1.getContact());

        Address addedAddress2 = contact.getAddresses().get(1);
        assertEquals(address2.getAddress(), addedAddress2.getAddress());
        assertEquals(address2.getCity(), addedAddress2.getCity());
        assertEquals(address2.getCountry(), addedAddress2.getCountry());
        assertEquals(address2.getContact(), addedAddress2.getContact());

        Phone addedPhone1 = contact.getPhones().get(0);
        assertEquals(phone1.getContact(), addedPhone1.getContact());
        assertEquals(phone1.getId(), addedPhone1.getId());
        assertEquals(phone1.getCountryCode(), addedPhone1.getCountryCode());
        assertEquals(phone1.getTelephoneNumber(), addedPhone1.getTelephoneNumber());

        Phone addedPhone2 = contact.getPhones().get(1);
        assertEquals(phone2.getContact(), addedPhone2.getContact());
        assertEquals(phone2.getId(), addedPhone2.getId());
        assertEquals(phone2.getCountryCode(), addedPhone2.getCountryCode());
        assertEquals(phone2.getTelephoneNumber(), addedPhone2.getTelephoneNumber());

        Email addedEmail1 = contact.getEmails().get(0);
        assertEquals(email1.getEmail(), addedEmail1.getEmail());
        assertEquals(email1.getContact(), addedEmail1.getContact());
        assertEquals(email1.getId(), addedEmail1.getId());

        Email addedEmail2 = contact.getEmails().get(1);
        assertEquals(email2.getEmail(), addedEmail2.getEmail());
        assertEquals(email2.getContact(), addedEmail2.getContact());
        assertEquals(email2.getId(), addedEmail2.getId());


        iContactRepo.deleteById(1L);
    }

}