package com.telran.phonebookapi.dto.address;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
public class AddressToEditDto {
    @ApiModelProperty(example = "USA")
    @NotBlank(message = "{validation.country.default}")
    public String country;

    @ApiModelProperty(example = "Miami")
    @NotBlank(message = "{validation.city.default}")
    public String city;

    @ApiModelProperty(example = "3251 S Miami Ave")
    @NotBlank(message = "{validation.address.default}")
    public String address;

    @ApiModelProperty(example = "33129")
    @NotBlank(message = "{validation.index.default}")
    public String index;

    @ApiModelProperty(example = "false")
    public boolean isFavorite;
}
