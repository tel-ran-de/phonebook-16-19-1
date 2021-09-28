package com.telran.phonebookapi.dto.phonedto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
public class PhoneToAddDto {

    @NotBlank(message = "{validation.countyCode.default}")
    public String countryCode;
    @NotBlank(message = "{validation.telephoneNumber.default}")
    public String telephoneNumber;
    public boolean isFavorite;
    @Min(message = "{validation.contactId.default}", value = 0L)
    public long contactId;
}
