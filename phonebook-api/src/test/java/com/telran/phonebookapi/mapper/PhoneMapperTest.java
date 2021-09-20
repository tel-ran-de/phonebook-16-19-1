package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.phonedto.PhoneToDisplayDto;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.entity.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PhoneMapperTest {

    @InjectMocks
    private PhoneMapper phoneMapper;

    @Test
    public void testToDto() {
        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);
        Phone phone = new Phone("+49", "123456", true, contact);

        PhoneToDisplayDto mappedPhoneToDto = phoneMapper.toDisplayDto(phone);

        assertEquals(phone.getCountryCode(), mappedPhoneToDto.countryCode);
        assertEquals(phone.getTelephoneNumber(), mappedPhoneToDto.telephoneNumber);
        assertEquals(phone.isFavorite(), mappedPhoneToDto.isFavorite);
        assertEquals(phone.getId(), mappedPhoneToDto.id);
        assertEquals(phone.getContact().getId(), mappedPhoneToDto.contactId);
    }
}