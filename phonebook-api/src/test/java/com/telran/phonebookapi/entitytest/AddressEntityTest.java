package com.telran.phonebookapi.entitytest;

import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.entity.Group;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressEntityTest {

    @Test
    public void createAddressTest() {

        Contact contact = new Contact("marina", "mitunevich", 30, true, Group.HOME);

        Address address = new Address("Germany", "Hamburg", "Ludwig 13", "1957", false, contact);

        assertEquals(contact, address.getContact());

    }

    @Test
    public void changeFieldCountryOfEmailEntityTest() {

        String country = "Germany";
        Contact contact = new Contact("marina", "berbner", 30, true, Group.HOME);
        Address address = new Address(country, "Hamburg", "Ludwig 13", "1957", false, contact);

        assertEquals(country, address.getCountry());

        String newCountry = "Belarus";
        address.setCountry(newCountry);

        assertEquals(newCountry, address.getCountry());

    }

    @Test
    public void changeFieldIndexOfEmailEntityTest() {

        String index = "1957";
        Contact contact = new Contact("marina", "berbner", 30, true, Group.HOME);
        Address address = new Address("country", "Hamburg", "Ludwig 13", index, false, contact);

        assertEquals(index, address.getIndex());

        String newIndex = "2222";
        address.setIndex(newIndex);

        assertEquals(newIndex, address.getIndex());

    }

    @Test
    public void changeFieldAddressOfEmailEntityTest() {

        String addressFiled = "Schleswiger 22";
        Contact contact = new Contact("marina", "berbner", 30, true, Group.HOME);
        Address address = new Address("Germany", "Hamburg", addressFiled, "1957", false, contact);

        assertEquals(addressFiled, address.getAddress());

        String newAddressFiled = "Ludwig 22";
        address.setAddress(newAddressFiled);

        assertEquals(newAddressFiled, address.getAddress());

    }
}
