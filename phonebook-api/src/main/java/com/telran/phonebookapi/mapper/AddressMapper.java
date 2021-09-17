package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.address.AddressToDisplayDto;
import com.telran.phonebookapi.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressToDisplayDto toDto(Address address) {
        return new AddressToDisplayDto(address.getId(), address.getCountry(), address.getCity(), address.getAddress(), address.getIndex(), address.isFavorite(), address.getContact().getId());
    }
}
