package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.repo.IContactRepo;
import com.telran.phonebookapi.repo.IEmailRepo;
import com.telran.phonebookapi.service.exceptions.AddressNotFoundException;
import com.telran.phonebookapi.service.exceptions.ContactNotFoundException;
import com.telran.phonebookapi.service.exceptions.EmailNotFountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    EmailService emailService;


    @Mock
    IEmailRepo emailRepo;
    @Mock
    IContactRepo contactRepo;

    @Test
    public void getAllEmailsOfContact_success() {

        emailService.getAllEmailsByContactIDd(1L);
        verify(emailRepo, times(1)).findAllByContactId(1L);
    }

    @Test
    public void testEdit_changeFields_success() {

        when(emailRepo.findById(2L)).thenReturn(Optional.of(new Email(
                "falco@gmail.com", true, new Contact("marina2", "bergner", 20,
                true, Group.HOME)
        )));
        emailService.editEmailById("marina@mail.ru", true, 2L);

        verify(emailRepo, times(1)).save(argThat(
                argument -> argument.getEmail().equals("marina@mail.ru") &&
                        argument.isFavorite() &&
                        argument.getContact().equals(new Contact("marina2", "bergner", 20,
                                true, Group.HOME))
        ));
    }

    @Test
    public void testEdit_changeFields_getException() {

        Exception exception = assertThrows(EmailNotFountException.class,
                () -> emailService.editEmailById("marina@mail.ru", false, 100L));

        assertNotNull(exception);
        assertEquals("email is not found", exception.getMessage());
    }

    @Test
    public void testGetEmailById_success() {

        when(emailRepo.findById(1L)).thenReturn(Optional.of(new Email(
                "falco@gmail.com", true, new Contact("marina2", "bergner", 20,
                true, Group.HOME)
        )));
        emailService.getEmailById(1L);
        verify(emailRepo, times(1)).findById(1L);
    }

    @Test
    public void testGetEmailById_emailNotFound() {

        Exception exception = assertThrows(EmailNotFountException.class,
                () -> emailService.getEmailById(200L));

        assertNotNull(exception);
        assertEquals("email is not found", exception.getMessage());
    }

    @Test
    public void testDeleteEmailById_success() {

        when(emailRepo.existsById(1L)).thenReturn(true);
        emailService.deleteEmailById(1L);
        verify(emailRepo, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAddressById_addressNotFound() {

        Exception exception = assertThrows(EmailNotFountException.class,
                () -> emailService.deleteEmailById(20L));
        assertNotNull(exception);
        assertEquals("email is not found", exception.getMessage());
    }

    @Test
    public void testAddEmail() {

        when(contactRepo.findById(2L)).thenReturn(Optional.of(new Contact("marina2", "bergner", 20,
                true, Group.HOME)));

        emailService.addEmail("falco@gmail.com", true, 2L);

        Email email = new Email("falco@gmail.com",
                true, new Contact("marina2", "bergner", 20,
                true, Group.HOME));

        verify(emailRepo, times(1)).save(email);
    }

    @Test
    public void testAddAddress_contactNotFound() {

        Exception exception = assertThrows(ContactNotFoundException.class,
                () -> emailService.addEmail("falco@gmail.com", true, 20L));
        assertNotNull(exception);
        assertEquals("required contact is not found", exception.getMessage());
    }

}
