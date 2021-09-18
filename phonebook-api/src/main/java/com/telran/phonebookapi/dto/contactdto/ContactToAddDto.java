package com.telran.phonebookapi.dto.contactdto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ContactToAddDto {

    public String firstName;
    public String lastName;
    public int age;
    public boolean isFavorite;
    public String group;
}
