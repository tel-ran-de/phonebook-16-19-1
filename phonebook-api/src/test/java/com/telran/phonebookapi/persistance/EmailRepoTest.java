package com.telran.phonebookapi.persistance;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.repo.IEmailRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class EmailRepoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private IEmailRepo emailRepo;

    @Test
    public void testAdd_addAddressToContact_oneRecordFound() {
        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);
        Email email = new Email("validEmail@save.com", true, contact);

        entityManager.persist(contact);
        entityManager.persist(email);
        entityManager.flush();
        entityManager.clear();

        List<Email> emails = (List<Email>) emailRepo.findAll();

        assertEquals(1, emails.size());
        assertEquals(email.getEmail(), emails.get(0).getEmail());

        assertEquals(email.getContact().getFirstName(), emails.get(0).getContact().getFirstName());
        assertEquals(email.getContact().getLastName(), emails.get(0).getContact().getLastName());
        assertEquals(1, emails.get(0).getContact().getEmails().size());
    }

    @Test
    public void testAdd_addAddressToContact_noRecordFound() {
        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);

        entityManager.persist(contact);
        entityManager.flush();
        entityManager.clear();

        List<Email> repoAddress = (List<Email>) emailRepo.findAll();

        assertEquals(0, repoAddress.size());
    }

    @Test
    public void testFindAllByContactId() {

        Contact contact1 = new Contact("Mikhail", "Bolender",
                30, false, Group.HOME);
        Contact contact2 = new Contact("Marina", "Mitunevich", 29, true, Group.HOME);
        Email email1 = new Email("validEmail@save.com", false, contact1);
        Email email2 = new Email("marina@mail.com", false, contact1);
        Email email3 = new Email("new@save.com", true, contact2);

        entityManager.persist(contact1);
        entityManager.persist(contact2);
        entityManager.persist(email1);
        entityManager.persist(email2);
        entityManager.persist(email3);
        entityManager.flush();
        entityManager.clear();

        List<Email> emails = (List<Email>) emailRepo.findAll();
        List<Email> emailsByContactId = emailRepo.findAllByContactId(contact2.getId());

        assertEquals(3, emails.size());
        assertEquals(1, emailsByContactId.size());

        assertEquals(contact2.getFirstName(), emailsByContactId.get(0).getContact().getFirstName());
        assertEquals(contact2.getLastName(), emailsByContactId.get(0).getContact().getLastName());
    }
}
