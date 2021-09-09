package com.telran.phonebookapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private String countryCode;

    @Setter
    private String telephoneNumber;

    @Setter
    private boolean isFavorite;

    @ManyToOne
    Contact contact;

    public Phone(@NonNull String countryCode, @NonNull String telephoneNumber, boolean isFavorite, @NonNull Contact contact) {
        this.countryCode = countryCode;
        this.telephoneNumber = telephoneNumber;
        this.isFavorite = isFavorite;
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return id == phone.id && isFavorite == phone.isFavorite && Objects.equals(countryCode, phone.countryCode) && Objects.equals(telephoneNumber, phone.telephoneNumber) && Objects.equals(contact, phone.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryCode, telephoneNumber, isFavorite, contact);
    }

}
