package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.repo.IAddressRepo;
import com.telran.phonebookapi.repo.IContactRepo;
import com.telran.phonebookapi.service.exceptions.AddressNotFoundException;
import com.telran.phonebookapi.service.exceptions.ContactNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private static final String ADDRESS_NOT_FOUND = "address is not found";
    private static final String CONTACT_NOT_FOUND = "required contact is not found";
    private IAddressRepo addressRepo;
    private IContactRepo contactRepo;

    public AddressService(IAddressRepo iAddressRepo, IContactRepo contactRepo) {
        this.addressRepo = iAddressRepo;
        this.contactRepo = contactRepo;
    }

    public List<Address> getAllAddressOfContact(long contactId) {

        if(!contactRepo.existsById(contactId))
         throw new ContactNotFoundException(CONTACT_NOT_FOUND);
        return addressRepo.findAllByContactId(contactId);
    }

    public Address addAddress(String country, String city, String address, String index, boolean isFavorite, long contactId) {

        Contact contact = contactRepo.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(CONTACT_NOT_FOUND));

        Address addressToSave = new Address(country, city, address, index, isFavorite, contact);
        return addressRepo.save(addressToSave);

    }

    public void deleteAddressById(long addressId) {

        if (addressRepo.existsById(addressId))
            addressRepo.deleteById(addressId);
        else
            throw new AddressNotFoundException(ADDRESS_NOT_FOUND);

    }

    public void editAddressById(String country, String city, String address, String index, boolean isFavorite, long addressId) {

        Address addressToEdit = addressRepo.findById(addressId)
                .orElseThrow(() -> new
                        AddressNotFoundException(ADDRESS_NOT_FOUND));

        addressToEdit.setCountry(country);
        addressToEdit.setCity(city);
        addressToEdit.setAddress(address);
        addressToEdit.setIndex(index);
        addressToEdit.setFavorite(isFavorite);

        addressRepo.save(addressToEdit);

    }

    public Address getAddressById(long addressId) {

        return addressRepo.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(ADDRESS_NOT_FOUND));

    }
}
