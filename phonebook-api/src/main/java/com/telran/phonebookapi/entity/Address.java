package com.telran.phonebookapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @NotBlank(message = "{validation.country.default}")
    private String country;

    @Setter
    @NotBlank(message = "{validation.city.default}")
    private String city;

    @Setter
    @NotBlank(message = "{validation.address.default}")
    private String address;

    @Setter
    @NotBlank(message = "{validation.index.default}")
    private String index;

    @Setter
    private boolean isFavorite;

    @ManyToOne
    Contact contact;

    public Address(@NonNull String country, @NonNull String city, @NonNull String address,
                   @NonNull String index, boolean isFavorite, @NonNull Contact contact) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.index = index;
        this.isFavorite = isFavorite;
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return id == address1.id && isFavorite == address1.isFavorite && Objects.equals(country, address1.country) && Objects.equals(city, address1.city) && Objects.equals(address, address1.address) && Objects.equals(index, address1.index) && Objects.equals(contact, address1.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, address, index, isFavorite, contact);
    }

}
