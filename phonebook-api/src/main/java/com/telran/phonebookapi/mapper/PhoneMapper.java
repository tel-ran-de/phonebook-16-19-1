package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.phonedto.PhoneToDisplayDto;
import com.telran.phonebookapi.entity.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {
    public PhoneToDisplayDto toDisplayDto(Phone phone) {
        return new PhoneToDisplayDto(
                phone.getId(),
                phone.getCountryCode(),
                phone.getTelephoneNumber(),
                phone.isFavorite(),
                phone.getContact().getId());
    }
}
