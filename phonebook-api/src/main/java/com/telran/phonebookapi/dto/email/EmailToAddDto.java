package com.telran.phonebookapi.dto.email;

import com.telran.phonebookapi.validation.Email;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
public class EmailToAddDto {

    @Email(message = "incorrect Email")
    @NotBlank(message = "{validation.email.default}")
    public String email;
    public boolean isFavorite;
    @Min(message = "{validation.contactId.default}", value = 0L)
    public long contactId;
}
