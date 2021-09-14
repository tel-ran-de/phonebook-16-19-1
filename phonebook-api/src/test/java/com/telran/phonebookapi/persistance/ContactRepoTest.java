package com.telran.phonebookapi.persistance;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.repo.IContactRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ContactRepoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private IContactRepo contactRepo;

    @Test
    public void testAdd_addAddressToContact_oneRecordFound() {
        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);

        entityManager.persist(contact);
        entityManager.flush();
        entityManager.clear();

        List<Contact> contacts = (List<Contact>) contactRepo.findAll();

        assertEquals(1, contacts.size());

        assertEquals(contact.getFirstName(), contacts.get(0).getFirstName());
        assertEquals(contact.getLastName(), contacts.get(0).getLastName());
        assertEquals(contact.getAge(), contacts.get(0).getAge());
        assertEquals(contact.getGroup(), contacts.get(0).getGroup());
        assertEquals(contact.isFavourite(), contacts.get(0).isFavourite());

        assertEquals(0, contacts.get(0).getEmails().size());
        assertEquals(0, contacts.get(0).getAddresses().size());
        assertEquals(0, contacts.get(0).getPhones().size());
    }

    @Test
    public void testAdd_addAddressToContact_noRecordFound() {
        List<Contact> repoAddress = (List<Contact>) contactRepo.findAll();
        assertEquals(0, repoAddress.size());
    }
}
