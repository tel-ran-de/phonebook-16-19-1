package com.telran.phonebookapi.dto.phonedto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
public class PhoneToEditDto {
    public long id;
    @NotBlank(message = "{validation.countyCode.default}")
    public String countryCode;
    @NotBlank(message = "{validation.telephoneNumber.default}")
    public String telephoneNumber;
    public boolean isFavorite;
}
