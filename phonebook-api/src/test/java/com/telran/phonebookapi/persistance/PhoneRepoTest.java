package com.telran.phonebookapi.persistance;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.entity.Phone;
import com.telran.phonebookapi.repo.IPhoneRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PhoneRepoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private IPhoneRepo phoneRepo;

    @Test
    public void testAdd_addAddressToContact_oneRecordFound() {
        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);
        Phone phone = new Phone("+49", "0176123123123", true, contact);

        entityManager.persist(contact);
        entityManager.persist(phone);
        entityManager.flush();
        entityManager.clear();

        List<Phone> phones = (List<Phone>) phoneRepo.findAll();

        assertEquals(1, phones.size());
        assertEquals(phone.getCountryCode(), phones.get(0).getCountryCode());
        assertEquals(phone.getTelephoneNumber(), phones.get(0).getTelephoneNumber());

        assertEquals(phone.getContact().getFirstName(), phones.get(0).getContact().getFirstName());
        assertEquals(phone.getContact().getLastName(), phones.get(0).getContact().getLastName());
        assertEquals(1, phones.get(0).getContact().getPhones().size());
    }

    @Test
    public void testAdd_addAddressToContact_noRecordFound() {
        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);

        entityManager.persist(contact);
        entityManager.flush();
        entityManager.clear();

        List<Phone> repoAddress = (List<Phone>) phoneRepo.findAll();

        assertEquals(0, repoAddress.size());
    }

}
