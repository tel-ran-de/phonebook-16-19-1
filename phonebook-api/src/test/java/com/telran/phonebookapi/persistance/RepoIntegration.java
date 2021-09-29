package com.telran.phonebookapi.persistance;

import com.telran.phonebookapi.entity.*;
import com.telran.phonebookapi.repo.IAddressRepo;
import com.telran.phonebookapi.repo.IContactRepo;
import com.telran.phonebookapi.repo.IEmailRepo;
import com.telran.phonebookapi.repo.IPhoneRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RepoIntegration {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IContactRepo contactRepo;
    @Autowired
    private IAddressRepo addressRepo;
    @Autowired
    private IEmailRepo emailRepo;
    @Autowired
    private IPhoneRepo phoneRepo;

    @Test
    public void tes() {
        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);
        entityManager.persist(contact);

        flushAndClear();

        List<Contact> contacts = contactRepo.findAll();
        assertEquals(1, contacts.size());
        assertEquals(contact.getLastName(), contacts.get(0).getLastName());
        assertEquals(0, contacts.get(0).getPhones().size());
        assertEquals(0, contacts.get(0).getAddresses().size());
        assertEquals(0, contacts.get(0).getEmails().size());

        addAddress(contact);
        addEmail(contact);
        addPhone(contact);
        addSecondContact(contact);
    }

    private void addPhone(Contact contact) {
        Phone phone = new Phone("+49", "0176123123123", true, contact);
        entityManager.persist(phone);
        flushAndClear();

        List<Phone> phones = (List<Phone>) phoneRepo.findAll();
        List<Contact> contacts = contactRepo.findAll();

        assertEquals(1, contacts.size());

        assertEquals(contact.getFirstName(), contacts.get(0).getFirstName());
        assertEquals(contact.getLastName(), contacts.get(0).getLastName());
        assertEquals(contact.getAge(), contacts.get(0).getAge());
        assertEquals(contact.getGroup(), contacts.get(0).getGroup());
        assertEquals(contact.isFavourite(), contacts.get(0).isFavourite());

        assertEquals(1, contacts.get(0).getPhones().size());
        assertEquals(1, contacts.get(0).getAddresses().size());
        assertEquals(1, contacts.get(0).getEmails().size());

        assertEquals(1, phones.size());
        assertEquals(phone.getCountryCode(), phones.get(0).getCountryCode());
        assertEquals(phone.getTelephoneNumber(), phones.get(0).getTelephoneNumber());

        assertEquals(phone.getContact().getFirstName(), phones.get(0).getContact().getFirstName());
        assertEquals(phone.getContact().getLastName(), phones.get(0).getContact().getLastName());
        assertEquals(1, phones.get(0).getContact().getPhones().size());
    }

    private void addEmail(Contact contact) {
        Email email = new Email("validemail@save.com", true, contact);

        entityManager.persist(email);
        flushAndClear();

        List<Email> emails = (List<Email>) emailRepo.findAll();
        List<Contact> contacts = contactRepo.findAll();

        assertEquals(1, contacts.size());

        assertEquals(contact.getFirstName(), contacts.get(0).getFirstName());
        assertEquals(contact.getLastName(), contacts.get(0).getLastName());
        assertEquals(contact.getAge(), contacts.get(0).getAge());
        assertEquals(contact.getGroup(), contacts.get(0).getGroup());
        assertEquals(contact.isFavourite(), contacts.get(0).isFavourite());

        assertEquals(0, contacts.get(0).getPhones().size());
        assertEquals(1, contacts.get(0).getAddresses().size());
        assertEquals(1, contacts.get(0).getEmails().size());

        assertEquals(1, emails.size());
        assertEquals(email.getEmail(), emails.get(0).getEmail());

        assertEquals(email.getContact().getFirstName(), emails.get(0).getContact().getFirstName());
        assertEquals(email.getContact().getLastName(), emails.get(0).getContact().getLastName());
        assertEquals(1, emails.get(0).getContact().getEmails().size());
    }

    private void addAddress(Contact contact) {
        Address address = new Address(
                "Germany",
                "Berlin",
                "Südekumzeile",
                "13591",
                true, contact);
        entityManager.persist(address);
        flushAndClear();

        List<Address> addresses = (List<Address>) addressRepo.findAll();
        List<Contact> contacts = contactRepo.findAll();

        assertEquals(1, contacts.size());

        assertEquals(contact.getFirstName(), contacts.get(0).getFirstName());
        assertEquals(contact.getLastName(), contacts.get(0).getLastName());
        assertEquals(contact.getAge(), contacts.get(0).getAge());
        assertEquals(contact.getGroup(), contacts.get(0).getGroup());
        assertEquals(contact.isFavourite(), contacts.get(0).isFavourite());

        assertEquals(0, contacts.get(0).getPhones().size());
        assertEquals(1, contacts.get(0).getAddresses().size());
        assertEquals(0, contacts.get(0).getEmails().size());

        assertEquals(1, addresses.size());
        assertEquals(address.getAddress(), addresses.get(0).getAddress());
        assertEquals(address.getCity(), addresses.get(0).getCity());
        assertEquals(address.getCountry(), addresses.get(0).getCountry());
        assertEquals(address.getIndex(), addresses.get(0).getIndex());

        assertEquals(address.getContact().getFirstName(), addresses.get(0).getContact().getFirstName());
        assertEquals(address.getContact().getLastName(), addresses.get(0).getContact().getLastName());
        assertEquals(1, addresses.get(0).getContact().getAddresses().size());
    }

    private void addSecondContact(Contact contact) {
        Contact contact2 = new Contact("Max", "Mustermann", 25, true, Group.PRIVATE);
        entityManager.persist(contact2);
        flushAndClear();

        List<Contact> contacts = contactRepo.findAll();

        assertEquals(2, contacts.size());

        assertEquals(contact.getFirstName(), contacts.get(0).getFirstName());
        assertEquals(contact.getLastName(), contacts.get(0).getLastName());
        assertEquals(contact.getAge(), contacts.get(0).getAge());
        assertEquals(contact.getGroup(), contacts.get(0).getGroup());
        assertEquals(contact.isFavourite(), contacts.get(0).isFavourite());

        assertEquals(contact2.getFirstName(), contacts.get(1).getFirstName());
        assertEquals(contact2.getLastName(), contacts.get(1).getLastName());
        assertEquals(contact2.getAge(), contacts.get(1).getAge());
        assertEquals(contact2.getGroup(), contacts.get(1).getGroup());
        assertEquals(contact2.isFavourite(), contacts.get(1).isFavourite());

        assertEquals(1, contacts.get(0).getPhones().size());
        assertEquals(1, contacts.get(0).getAddresses().size());
        assertEquals(1, contacts.get(0).getEmails().size());

        assertEquals(0, contacts.get(1).getPhones().size());
        assertEquals(0, contacts.get(1).getAddresses().size());
        assertEquals(0, contacts.get(1).getEmails().size());
    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}
