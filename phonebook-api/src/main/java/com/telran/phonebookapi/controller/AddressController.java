package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.address.AddressToAddDto;
import com.telran.phonebookapi.dto.address.AddressToDisplayDto;
import com.telran.phonebookapi.dto.address.AddressToEditDto;
import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.mapper.AddressMapper;
import com.telran.phonebookapi.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/address")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;


    public AddressController(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    @PostMapping
    public AddressToDisplayDto add(@RequestBody AddressToAddDto addressToAdd) {
        Address address = addressService.addAddress(addressToAdd.country, addressToAdd.city, addressToAdd.address, addressToAdd.index,
                addressToAdd.isFavorite, addressToAdd.contactId);
        return addressMapper.toDto(address);
    }

    @PutMapping("/{id}")
    public void edit(@RequestBody AddressToEditDto addressToAdd, @PathVariable(name = "id") long addressId) {
        addressService.editAddressById(addressToAdd.country, addressToAdd.city, addressToAdd.address, addressToAdd.index,
                addressToAdd.isFavorite, addressId);
    }

    @GetMapping("/{id}")
    public AddressToDisplayDto getById(@PathVariable(name = "id") long addressId) {
        Address address = addressService.getAddressById(addressId);
        return addressMapper.toDto(address);
    }


    @GetMapping("/{id}/all")
    public List<AddressToDisplayDto> getAllByContact(@PathVariable(name = "id") long contactId) {
        List<Address> address = addressService.getAllAddressOfContact(contactId);
        return address.stream().map(addressMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long addressId) {
        addressService.deleteAddressById(addressId);
    }
}
