package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.address.AddressToAddDto;
import com.telran.phonebookapi.dto.address.AddressToDisplayDto;
import com.telran.phonebookapi.dto.address.AddressToEditDto;
import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.mapper.AddressMapper;
import com.telran.phonebookapi.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Address API")
@RestController
@RequestMapping("api/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;


    public AddressController(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    @ApiOperation(value = "Add a new address to contact", tags = {"add"})
    @PostMapping
    public AddressToDisplayDto add(@RequestBody @Valid @ApiParam(name = "AddressToAddDto", value = "Address information for a new address to be created") AddressToAddDto addressToAdd) {
        Address address = addressService.addAddress(addressToAdd.country, addressToAdd.city, addressToAdd.address, addressToAdd.index,
                addressToAdd.isFavorite, addressToAdd.contactId);
        return addressMapper.toDto(address);
    }

    @ApiOperation(value = "Update address by id", tags = {"edit"})
    @PutMapping("/{id}")
    public void edit(@RequestBody @Valid @ApiParam(name = "AddressToEditDto", value = "Address information to be updated") AddressToEditDto addressToAdd,
                     @ApiParam(name = "id", example = "1", value = "Address id to be update") @PathVariable(name = "id") long addressId) {
        addressService.editAddressById(addressToAdd.country, addressToAdd.city, addressToAdd.address, addressToAdd.index,
                addressToAdd.isFavorite, addressId);
    }

    @ApiOperation(value = "Get address by id", tags = {"get by id"})
    @GetMapping("/{id}")
    public AddressToDisplayDto getById(@ApiParam("address id") @PathVariable(name = "id") long addressId) {
        Address address = addressService.getAddressById(addressId);
        return addressMapper.toDto(address);
    }

    @ApiOperation(value = "Get all addresses by contact id", notes = "Status 400 if contact not exist", tags = {"get all"})
    @GetMapping("/{id}/all")
    public List<AddressToDisplayDto> getAllByContact(@ApiParam(name = "id", value = "Contact id to find all address by this contact id", example = "1") @PathVariable(name = "id") long contactId) {
        List<Address> address = addressService.getAllAddressOfContact(contactId);
        return address.stream().map(addressMapper::toDto).collect(Collectors.toList());
    }

    @ApiOperation(value = "Remove address by id", tags = {"remove"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long addressId) {
        addressService.deleteAddressById(addressId);
    }
}
