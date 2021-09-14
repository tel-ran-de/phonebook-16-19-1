package com.telran.phonebookapi.persistance;

import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.repo.IAddressRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AddressRepoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private IAddressRepo iAddressRepo;

    @Test
    public void testAdd_addAddressToContact_oneRecordFound() {
        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);
        Address address = new Address(
                "Germany",
                "Berlin",
                "Südekumzeile",
                "13591",
                true, contact);

        entityManager.persist(contact);
        entityManager.persist(address);
        entityManager.flush();
        entityManager.clear();

        List<Address> addresses = (List<Address>) iAddressRepo.findAll();

        assertEquals(1, addresses.size());
        assertEquals(address.getAddress(), addresses.get(0).getAddress());
        assertEquals(address.getCity(), addresses.get(0).getCity());
        assertEquals(address.getCountry(), addresses.get(0).getCountry());
        assertEquals(address.getIndex(), addresses.get(0).getIndex());

        assertEquals(address.getContact().getFirstName(), addresses.get(0).getContact().getFirstName());
        assertEquals(address.getContact().getLastName(), addresses.get(0).getContact().getLastName());
        assertEquals(1, addresses.get(0).getContact().getAddresses().size());
    }

    @Test
    public void testAdd_addAddressToContact_noRecordFound() {
        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);

        entityManager.persist(contact);
        entityManager.flush();
        entityManager.clear();

        List<Address> repoAddress = (List<Address>) iAddressRepo.findAll();

        assertEquals(0, repoAddress.size());
    }
}
