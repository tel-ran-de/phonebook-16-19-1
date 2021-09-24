package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.email.EmailToDisplayDto;
import com.telran.phonebookapi.entity.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public EmailToDisplayDto toDisplayDto(Email email) {
        return new EmailToDisplayDto(email.getId(),
                email.getEmail(),
                email.isFavorite(),
                email.getContact().getId());
    }
}
