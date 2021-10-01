package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.contactdto.ContactToAddDto;
import com.telran.phonebookapi.dto.contactdto.ContactToDisplayDto;
import com.telran.phonebookapi.dto.contactdto.ContactToEditDto;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.mapper.ContactMapper;
import com.telran.phonebookapi.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Contact API")
@RestController
@RequestMapping("api/contacts")
public class ContactController {

    private final ContactService contactService;
    private final ContactMapper contactMapper;

    public ContactController(ContactService contactService, ContactMapper contactMapper) {
        this.contactService = contactService;
        this.contactMapper = contactMapper;
    }

    @ApiOperation(value = "Add a new contact to contact", tags = {"add"})
    @PostMapping
    public ContactToDisplayDto add(@RequestBody @Valid ContactToAddDto contactToAdd) {

        Contact contact = contactService.add(contactToAdd.firstName, contactToAdd.lastName, contactToAdd.age,
                contactToAdd.isFavorite, Group.valueOf(contactToAdd.group.toUpperCase()));
        return contactMapper.toDto(contact);
    }

    @ApiOperation(value = "Update contact by id", tags = {"edit"})
    @PutMapping("/{id}")
    public void edit(@RequestBody ContactToEditDto contactToEditDto, @PathVariable(name = "id") long contactId) {

        contactService.editById(contactToEditDto.firstName, contactToEditDto.lastName, contactToEditDto.age,
                contactToEditDto.isFavorite,
                Group.valueOf(contactToEditDto.group.toUpperCase()), contactId);
    }

    @ApiOperation(value = "Get contact by id", tags = {"get by id"})
    @GetMapping("/{id}")
    public ContactToDisplayDto getById(@PathVariable(name = "id") long contactId) {

        Contact contact = contactService.getById(contactId);
        return contactMapper.toDto(contact);
    }

    @ApiOperation(value = "Get all contacts", tags = {"get all"})
    @GetMapping
    public List<ContactToDisplayDto> getAllContacts() {

        return contactService.getAllContacts().stream()
                .map(contactMapper::toDto).collect(Collectors.toList());
    }

    @ApiOperation(value = "Remove contact by id", tags = {"remove"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long contactId) {

        contactService.deleteById(contactId);
    }
}
