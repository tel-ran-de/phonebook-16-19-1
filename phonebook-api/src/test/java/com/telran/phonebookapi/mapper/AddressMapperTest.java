package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.address.AddressToDisplayDto;
import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AddressMapperTest {

    @InjectMocks
    private AddressMapper addressMapper;

    @Test
    public void testToDto() {
        Contact contact = new Contact("Max", "Mustermann", 25, true, Group.HOME);
        Address address = new Address("Germany", "Berlin", "Hauptstrasse 125", "10055", true, contact);

        AddressToDisplayDto mappedAddressToDto = addressMapper.toDto(address);

        assertEquals(address.getCountry(), mappedAddressToDto.country);
        assertEquals(address.getCity(), mappedAddressToDto.city);
        assertEquals(address.getAddress(), mappedAddressToDto.address);
        assertEquals(address.getIndex(), mappedAddressToDto.index);
        assertEquals(address.isFavorite(), mappedAddressToDto.isFavorite);
        assertEquals(address.getContact().getId(), mappedAddressToDto.contactId);
    }
}
