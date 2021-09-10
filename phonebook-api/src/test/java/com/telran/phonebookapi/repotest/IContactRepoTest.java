package com.telran.phonebookapi.repotest;

import com.telran.phonebookapi.entity.*;
import com.telran.phonebookapi.repo.IContactRepo;
import com.telran.phonebookapi.repo.IPhoneRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IContactRepoTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    IContactRepo iContactRepo;

    Contact contact;

    @Test
    public void addContact() {

        contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);

        entityManager.persist(contact);

        entityManager.flush();
        entityManager.clear();

        List<Contact> contacts = iContactRepo.findAll();

        assertEquals(1, contacts.size());
        assertEquals(1, contact.getId());
    }

    @Test
    public void editContact() {

        contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);

        Phone phone1 = new Phone("+49", "12345", true, contact);

        Email email1 = new Email("bolenderm91@gmail.com", true, contact);

        Address address1 = new Address(
                "Germany",
                "Berlin",
                "Südekumzeile",
                "13591",
                true, contact);

        contact.addPhone(phone1);
        contact.addEmail(email1);
        contact.addAddress(address1);

        entityManager.persist(contact);
        entityManager.persist(phone1);
        entityManager.persist(email1);
        entityManager.persist(address1);

        entityManager.flush();
        entityManager.clear();

        assertEquals(1, contact.getPhones().size());
        assertEquals(1, contact.getAddresses().size());
        assertEquals(1, contact.getEmails().size());

        Phone phone2 = new Phone("+35", "123456", true, contact);

        Phone phone3 = new Phone("+15", "123457", true, contact);

        Email email2 = new Email("stormblade91@gmail.com", true, contact);

        Address address2 = new Address(
                "Russia",
                "Moscow",
                "Lenina",
                "169300",
                false, contact);

        contact.addPhone(phone2);
        contact.addPhone(phone3);
        contact.addEmail(email2);
        contact.addAddress(address2);

        entityManager.persist(phone2);
        entityManager.persist(phone3);
        entityManager.persist(address2);
        entityManager.persist(email2);

        entityManager.flush();
        entityManager.clear();

        assertEquals(3, contact.getPhones().size());
        assertEquals(2, contact.getEmails().size());
        assertEquals(2, contact.getAddresses().size());
    }

}