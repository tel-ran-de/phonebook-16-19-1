package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.contactdto.ContactToDisplayDto;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ContactMapperTest {

    @InjectMocks
    private ContactMapper contactMapper;

    @Test
    public void testToDto() {

        Contact contact = new Contact("Marina", "Mitunevich", 30, true, Group.HOME);

        ContactToDisplayDto contactToDisplayDto = contactMapper.toDto(contact);

        assertEquals(contact.getFirstName(), contactToDisplayDto.firstName);
        assertEquals(contact.getLastName(), contactToDisplayDto.lastName);
        assertEquals(contact.getAge(), contactToDisplayDto.age);
        assertEquals(contact.isFavourite(), contactToDisplayDto.isFavorite);
        assertEquals(contact.getGroup().getGroupName(), contactToDisplayDto.group);
    }
}
