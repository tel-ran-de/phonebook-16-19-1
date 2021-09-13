package com.telran.phonebookapi.entitytest;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.entity.Phone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneEntityTest {

    @Test
    public void createPhoneEntityTest() {

        Contact contact = new Contact("marina", "mitunevich", 30, true, Group.HOME);

        Phone phone = new Phone("Ger", "067896546", false, contact);

        assertEquals(contact, phone.getContact());

    }

    @Test
    public void changeFieldCountryCodeOfPhoneEntityTest() {

        String countryCode = "123";
        Contact contact = new Contact("marina", "mitunevich", 30, true, Group.HOME);

        Phone phone = new Phone(countryCode, "067896546", false, contact);
        assertEquals(countryCode, phone.getCountryCode());

        String newCountryCode = "345";
        phone.setCountryCode(newCountryCode);

        assertEquals(newCountryCode, phone.getCountryCode());

    }

    @Test
    public void changeFieldTelephoneNumberOfPhoneEntityTest() {

        String telNumber = "12357909";
        Contact contact = new Contact("marina", "mitunevich", 30, true, Group.HOME);

        Phone phone = new Phone("countryCode", telNumber, false, contact);
        assertEquals(telNumber, phone.getTelephoneNumber());

        String newTelNumber = "3458888888";
        phone.setTelephoneNumber(newTelNumber);

        assertEquals(newTelNumber, phone.getTelephoneNumber());

    }

}
