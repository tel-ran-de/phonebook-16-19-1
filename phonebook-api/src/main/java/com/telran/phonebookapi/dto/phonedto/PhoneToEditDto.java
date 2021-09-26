package com.telran.phonebookapi.dto.phonedto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PhoneToEditDto {
    public long id;
    public String countryCode;
    public String telephoneNumber;
    public boolean isFavorite;
}
