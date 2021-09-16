package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.repo.IContactRepo;
import com.telran.phonebookapi.service.exceptions.ContactNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

    @InjectMocks
    ContactService contactService;

    @Mock
    IContactRepo repo;

    @Test
    public void testGetAllContact() {

        contactService.getAllContacts();
        verify(repo, times(1)).findAll();
    }

    @Test
    public void testEdit_changeFields_success() {

        when(repo.findById(1L)).thenReturn(Optional.of(new Contact("marina", "mitunevich", 30,
                false, Group.HOME)));
        contactService.editById("marina2", "bergner", 20,
                true, Group.HOME, 1L);

        verify(repo, times(1))
                .save(argThat(
                        argument -> argument.getFirstName().equals("marina2") &&
                                argument.getLastName().equals("bergner") &&
                                argument.getAge() == 20 &&
                                argument.isFavourite() &&
                                argument.getGroup().equals(Group.HOME)

                ));
    }

    @Test
    public void testEdit_changeFields_getException() {

        Exception exception = assertThrows(ContactNotFoundException.class,
                () -> contactService.editById("marina2", "bergner", 20,
                        true, Group.HOME, 90L));

        assertNotNull(exception);
        assertEquals("contact is not found", exception.getMessage());

    }

    @Test
    public void testGetById_success() {

        when(repo.findById(1L)).thenReturn(Optional.of(new Contact("marina", "mitunevich", 30,
                false, Group.HOME)));
        contactService.getById(1L);
        verify(repo, times(1)).findById(1L);

    }

    @Test
    public void testGetById_ContactNotFound() {

        Exception exception = assertThrows(ContactNotFoundException.class, () -> contactService.getById(80L));
        assertNotNull(exception);
        assertEquals("contact is not found", exception.getMessage());

    }

    @Test
    public void testDeleteById_success() {

        when(repo.existsById(1L)).thenReturn(true);
        contactService.deleteById(1L);
        verify(repo, times(1)).deleteById(1L);

    }

    @Test
    public void testDeleteById_ContactNotFound() {

        Exception exception = assertThrows(ContactNotFoundException.class,
                () -> contactService.deleteById(20L));
        assertNotNull(exception);
        assertEquals("contact is not found", exception.getMessage());

    }

    @Test
    public void testAddContact() {

        Contact contact = new Contact("marina", "mitunevich", 30,
                false, Group.HOME);

        contactService.add("marina", "mitunevich", 30,
                false, Group.HOME);

        verify(repo, times(1)).save(contact);

    }

}
