package com.telran.phonebookapi.dto.phonedto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
public class PhoneToAddDto {

    @NotBlank(message = "{validation.countyCode.default}")
    @Size(max = 10, message = "{validation.telephoneCodeLength.default}")
    public String countryCode;
    @NotNull
    @NotBlank(message = "{validation.telephoneNumber.default}")
    @Pattern(regexp = "^[0-9]+$", message = "{validation.telephoneNumberPattern.default}")
    @Size(min = 5, max = 30, message = "{validation.telephoneNumberLength.default}" )
    public String telephoneNumber;
    public boolean isFavorite;
    @Positive(message = "{validation.contactId.default}")
    public long contactId;
}
