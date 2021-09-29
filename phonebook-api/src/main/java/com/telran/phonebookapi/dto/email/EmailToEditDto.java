package com.telran.phonebookapi.dto.email;

import com.telran.phonebookapi.validation.Email;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
public class EmailToEditDto {

    @Email(message = "{validation.emailPattern.default}")
    @NotNull
    @NotBlank(message = "{validation.email.default}")
    public String email;
    public boolean isFavorite;
}
