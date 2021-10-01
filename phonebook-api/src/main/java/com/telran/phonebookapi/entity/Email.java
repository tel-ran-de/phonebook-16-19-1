package com.telran.phonebookapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;


@Entity
@Getter
@NoArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Setter
    @com.telran.phonebookapi.validation.Email(message = "{validation.emailPattern.default}")
    @NotBlank(message = "{validation.email.default}")
    private String email;

    @Setter
    private boolean isFavorite;

    @ManyToOne
    Contact contact;

    public Email(@NonNull String email, boolean isFavorite, @NonNull Contact contact) {
        this.email = email;
        this.isFavorite = isFavorite;
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return id == email1.id && isFavorite == email1.isFavorite && Objects.equals(email, email1.email) && Objects.equals(contact, email1.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, isFavorite, contact);
    }

}
