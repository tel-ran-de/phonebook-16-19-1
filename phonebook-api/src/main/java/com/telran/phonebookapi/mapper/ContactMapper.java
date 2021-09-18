package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.contactdto.ContactToDisplayDto;
import com.telran.phonebookapi.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public ContactToDisplayDto toDto(Contact contact){

        return new ContactToDisplayDto(contact.getId(), contact.getFirstName(), contact.getLastName(),
                contact.getAge(), contact.isFavourite(), contact.getGroup().getGroupName());
    }

}
