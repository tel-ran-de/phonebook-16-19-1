package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.entity.Phone;
import com.telran.phonebookapi.repo.IContactRepo;
import com.telran.phonebookapi.repo.IPhoneRepo;
import com.telran.phonebookapi.service.exceptions.ContactNotFoundException;
import com.telran.phonebookapi.service.exceptions.PhoneNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {
    @InjectMocks
    PhoneService phoneService;
    @Mock
    IPhoneRepo phoneRepo;
    @Mock
    IContactRepo contactRepo;

    @Test
    public void getAllPhones() {
        when(contactRepo.existsById(1L)).thenReturn(true);
        phoneService.getAllByContactId(1L);
        verify(phoneRepo, times(1)).findAllByContactId(1L);
    }

    @Test
    public void getAllPhonesContactNotFoundException_test() {
        Exception exception = assertThrows(ContactNotFoundException.class,
                () -> phoneService.getAllByContactId(50L));
        assertNotNull(exception);
        assertEquals("required contact is not found", exception.getMessage());
    }

    @Test
    public void editPhone_test() {
        when(phoneRepo.findById(1L)).thenReturn(Optional.of(new Phone("+49", "123456", true,
                new Contact("Mikhail", "Bolender", 30, true, Group.HOME))));
        phoneService.editById("+48", "654321", true, 1L);
        verify(phoneRepo, times(1)).save(argThat(argument -> argument
                .getCountryCode().equals("+48") &&
                argument.getTelephoneNumber().equals("654321") &&
                argument.isFavorite() &&
                argument.getContact().getFirstName().equals("Mikhail") &&
                argument.getContact().getLastName().equals("Bolender")));
    }

    @Test
    public void edit_exception_test() {
        Exception exception = assertThrows(PhoneNotFoundException.class,
                () -> phoneService.editById("+48", "654321", false, 50L));
        assertNotNull(exception);
        assertEquals("phone is not found", exception.getMessage());
    }

    @Test
    public void getPhoneById_test() {
        when(phoneRepo.findById(1L)).thenReturn(Optional.of(new Phone(
                "+49", "123456", true, new Contact("marina2", "bergner", 20,
                true, Group.HOME)
        )));
        phoneService.getById(1L);
        verify(phoneRepo, times(1)).findById(1L);
    }

    @Test
    public void phoneNotFoundException_test() {
        Exception exception = assertThrows(PhoneNotFoundException.class,
                () -> phoneService.getById(50L));
        assertNotNull(exception);
        assertEquals("phone is not found", exception.getMessage());
    }

    @Test
    public void deletePhoneById_test() {

        when(phoneRepo.existsById(1L)).thenReturn(true);
        phoneService.deleteById(1L);
        verify(phoneRepo, times(1)).deleteById(1L);
    }

    @Test
    public void deletePhoneByIdException_test() {
        Exception exception = assertThrows(PhoneNotFoundException.class,
                () -> phoneService.deleteById(50L));
        assertNotNull(exception);
        assertEquals("phone is not found", exception.getMessage());
    }

    @Test
    public void addPhone_test() {
        when(contactRepo.findById(1L)).thenReturn(Optional.of(new Contact("Mikhail", "Bolender", 30,
                true, Group.HOME)));
        phoneService.addPhone("+49", "123456", true, 1L);
        Phone phone = new Phone("+49", "123456",
                true, new Contact("Mikhail", "Bolender", 30,
                true, Group.HOME));
        verify(phoneRepo, times(1)).save(phone);
    }

    @Test
    public void addPhoneContactNotFoundException_test() {
        Exception exception = assertThrows(ContactNotFoundException.class,
                () -> phoneService.addPhone("+49", "123456", true, 50L));
        assertNotNull(exception);
        assertEquals("required contact is not found", exception.getMessage());
    }


}