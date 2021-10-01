package com.telran.phonebookapi.dto.email;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EmailToDisplayDto {

    public long id;
    public String email;
    public boolean isFavorite;
    public long contactId;
}
