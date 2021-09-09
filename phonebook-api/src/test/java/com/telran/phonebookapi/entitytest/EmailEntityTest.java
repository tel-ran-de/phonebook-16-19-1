package com.telran.phonebookapi.entitytest;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.entity.Group;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailEntityTest {

    @Test
    public void createEmailEntityTest() {

        Contact contact = new Contact("marina", "mitunevich", 30, true, Group.HOME);

        Email email = new Email("marina@mail.ru", true, contact);

        String myEmail = "marina@mail.ru";
        assertEquals(myEmail, email.getEmail());

    }

    @Test
    public void changeFieldEmailOfEmailEntityTest() {

        String emailField = "marina@mail.ru";

        Contact contact = new Contact("marina", "berbner", 30, true, Group.HOME);
        Email email = new Email(emailField, true, contact);

        assertEquals(emailField, email.getEmail());

        String newEmailField = "petya@mail.ru";
        email.setEmail(newEmailField);


        assertEquals(newEmailField, email.getEmail());

    }

    @Test
    public void changeFieldIsFavoriteOfEmailEntityTest() {

        boolean favourite = true;

        Contact contact = new Contact("marina", "berbner", 30, true, Group.HOME);
        Email email = new Email("petya@mail.ru", favourite, contact);

        assertEquals(favourite, email.isFavorite());

        boolean newFavourite = false;
        email.setFavorite(newFavourite);


        assertEquals(newFavourite, email.isFavorite());

    }

}
