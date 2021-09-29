package com.telran.phonebookapi.dto.address;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
public class AddressToAddDto {

    @NotBlank(message = "{validation.country.default}")
    public String country;
    @NotBlank(message = "{validation.city.default}")
    public String city;
    @NotBlank(message = "{validation.address.default}")
    public String address;
    @NotBlank(message = "{validation.index.default}")
    public String index;
    public boolean isFavorite;
    @Positive(message = "{validation.contactId.default}")
    public long contactId;
}
