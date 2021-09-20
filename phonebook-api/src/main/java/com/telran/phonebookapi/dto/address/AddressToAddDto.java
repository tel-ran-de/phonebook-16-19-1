package com.telran.phonebookapi.dto.address;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AddressToAddDto {
    public String country;
    public String city;
    public String address;
    public String index;
    public boolean isFavorite;
    public long contactId;
}
