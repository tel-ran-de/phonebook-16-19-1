package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.repo.IContactRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private static final String CONTACT_IS_NOT_FOUND = "contact is not found";
    private IContactRepo contactRepo;

    public ContactService(IContactRepo repo) {
        this.contactRepo = repo;
    }

    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }

    public Contact add(String firstName, String lastName, int age, boolean isFavorite, Group group) {

        Contact contact = new Contact(firstName, lastName, age, isFavorite, group);
        return contactRepo.save(contact);
    }

    public Contact getById(long contactId) {

        return contactRepo.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(CONTACT_IS_NOT_FOUND));

    }

    public void deleteById(long contactId) {

        if (contactRepo.existsById(contactId))
            contactRepo.deleteById(contactId);
        else
            throw new ContactNotFoundException(CONTACT_IS_NOT_FOUND);
    }

    public void editById(String firstName, String lastName, int age, boolean isFavorite, Group group, long contactId) {

        Contact contact = contactRepo.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(CONTACT_IS_NOT_FOUND));

        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setAge(age);
        contact.setFavourite(isFavorite);
        contact.setGroup(group);

        contactRepo.save(contact);

    }
}
