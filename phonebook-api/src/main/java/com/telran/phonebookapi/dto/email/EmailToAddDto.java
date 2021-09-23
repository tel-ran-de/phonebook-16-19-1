package com.telran.phonebookapi.dto.email;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EmailToAddDto {
    public String email;
    public boolean isFavorite;
    public long contactId;
}
