package com.telran.phonebookapi.dto.address;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
public class AddressToAddDto {
    @ApiModelProperty(example = "Germany")
    @NotBlank(message = "{validation.country.default}")
    public String country;

    @ApiModelProperty(example = "Berlin")
    @NotBlank(message = "{validation.city.default}")
    public String city;

    @ApiModelProperty(example = "Hauptstrasse 11")
    @NotBlank(message = "{validation.address.default}")
    public String address;
    
    @ApiModelProperty(example = "10369")
    @NotBlank(message = "{validation.index.default}")
    public String index;

    @ApiModelProperty(example = "false")
    public boolean isFavorite;

    @ApiModelProperty(notes = "Unique identifier of the contact", example = "1")
    @Positive(message = "{validation.contactId.default}")
    public long contactId;
}
