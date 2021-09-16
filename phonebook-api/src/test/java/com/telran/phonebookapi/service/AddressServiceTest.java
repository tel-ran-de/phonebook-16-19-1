package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.repo.IAddressRepo;
import com.telran.phonebookapi.repo.IContactRepo;
import com.telran.phonebookapi.service.exceptions.AddressNotFoundException;
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
public class AddressServiceTest {

    @InjectMocks
    AddressService addressService;

    @InjectMocks
    ContactService contactService;

    @Mock
    IAddressRepo addressRepo;
    @Mock
    IContactRepo contactRepo;

    @Test
    public void getAllAddressOfContact_success() {
        addressService.getAllAddressOfContact(1L);
        verify(addressRepo, times(1)).findAllByContactId(1L);
    }

    @Test
    public void testEdit_changeFields_success() {

        when(addressRepo.findById(1L)).thenReturn(Optional.of(
                new Address("Belarus", "Berlin", "Schleswiger", "1000",
                        false, new Contact("marina2", "bergner", 20,
                        true, Group.HOME))
        ));

        addressService.editAddressById("Germany", "Schwerin", "Schleswiger", "19057",
                true, 1L);
        verify(addressRepo, times(1)).save(argThat(
                argument -> argument.getCountry().equals("Germany") &&
                        argument.getCity().equals("Schwerin") &&
                        argument.getAddress().equals("Schleswiger") &&
                        argument.getIndex().equals("19057") &&
                        argument.isFavorite() &&
                        argument.getContact().equals(new Contact("marina2", "bergner", 20,
                                true, Group.HOME))
        ));
    }

    @Test
    public void testEdit_changeFields_getException() {

        Exception exception = assertThrows(AddressNotFoundException.class,
                () -> addressService.editAddressById("Germany", "Schwerin", "Schleswiger", "19057",
                        true, 100L));

        assertNotNull(exception);
        assertEquals("address is not found", exception.getMessage());

    }

    @Test
    public void testGetById_success() {

        when(addressRepo.findById(1L)).thenReturn(Optional.of(new Address("Belarus", "Berlin", "Schleswiger", "1000",
                false, new Contact("marina2", "bergner", 20,
                true, Group.HOME))));
        addressService.getAddressById(1L);
        verify(addressRepo, times(1)).findById(1L);

    }

    @Test
    public void testGetById_addressNotFound() {

        Exception exception = assertThrows(AddressNotFoundException.class, () -> addressService.getAddressById(80L));
        assertNotNull(exception);
        assertEquals("address is not found", exception.getMessage());

    }

    @Test
    public void testDeleteAddressById_success() {

        when(addressRepo.existsById(1L)).thenReturn(true);
        addressService.deleteAddressById(1L);
        verify(addressRepo, times(1)).deleteById(1L);

    }

    @Test
    public void testDeleteAddressById_addressNotFound() {

        Exception exception = assertThrows(AddressNotFoundException.class,
                () -> addressService.deleteAddressById(20L));
        assertNotNull(exception);
        assertEquals("address is not found", exception.getMessage());

    }

    @Test
    public void testAddAddress() {

        when(contactRepo.findById(2L)).thenReturn(Optional.of(new Contact("marina2", "bergner", 20,
                true, Group.HOME)));

        addressService.addAddress("Belarus", "Berlin", "Schleswiger", "1000",
                false, 2L);

        Address address = new Address("Belarus", "Berlin", "Schleswiger", "1000",
                false, new Contact("marina2", "bergner", 20,
                true, Group.HOME));

        verify(addressRepo, times(1)).save(address);

    }

    @Test
    public void testAddAddress_contactNotFound() {

        Exception exception = assertThrows(ContactNotFoundException.class,
                () -> addressService.addAddress("Belarus", "Berlin", "Schleswiger", "1000",
                        false, 200L));
        assertNotNull(exception);
        assertEquals("required contact is not found", exception.getMessage());

    }

}
