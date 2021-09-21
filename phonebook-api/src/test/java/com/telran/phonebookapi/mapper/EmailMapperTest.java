package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.email.EmailToDisplayDto;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.entity.Group;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmailMapperTest {

    @InjectMocks
    private EmailMapper emailMapper;

    @Test
    public void testToDto() {
        Contact contact = new Contact("Mikhail", "Bolender", 30, true, Group.HOME);
        Email email = new Email("valid.emailqemail.com", true, contact);

        EmailToDisplayDto mappedPhoneToDto = emailMapper.toDisplayDto(email);

        assertEquals(email.getEmail(), mappedPhoneToDto.email);
        assertEquals(email.isFavorite(), mappedPhoneToDto.isFavorite);
        assertEquals(email.getId(), mappedPhoneToDto.id);
        assertEquals(email.getContact().getId(), mappedPhoneToDto.contactId);
    }
}
