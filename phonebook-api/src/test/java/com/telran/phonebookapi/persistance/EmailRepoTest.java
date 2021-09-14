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
}
