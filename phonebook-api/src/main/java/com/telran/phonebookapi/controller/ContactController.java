package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.contactdto.ContactToAddDto;
import com.telran.phonebookapi.dto.contactdto.ContactToDisplayDto;
import com.telran.phonebookapi.dto.contactdto.ContactToEditDto;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.mapper.ContactMapper;
import com.telran.phonebookapi.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/contact")
public class ContactController {

    private final ContactService contactService;
    private final ContactMapper contactMapper;

    public ContactController(ContactService contactService, ContactMapper contactMapper) {
        this.contactService = contactService;
        this.contactMapper = contactMapper;
    }

    @GetMapping
    public List<ContactToDisplayDto> getAllContacts() {

        return contactService.getAllContacts().stream()
                .map(contactMapper ::toDto).collect(Collectors.toList());
    }

    @PostMapping
    public ContactToDisplayDto add(@RequestBody ContactToAddDto contactToAdd) {

        Contact contact = contactService.add(contactToAdd.firstName, contactToAdd.lastName, contactToAdd.age,
                contactToAdd.isFavorite, Group.valueOf(contactToAdd.group));
        return contactMapper.toDto(contact);
    }

    @PutMapping("/{id}")
    public void edit(@RequestBody ContactToEditDto contactToEditDto, @PathVariable(name = "id") long contactId) {

        contactService.editById(contactToEditDto.firstName, contactToEditDto.lastName, contactToEditDto.age,
                contactToEditDto.isFavorite,
                Group.valueOf(contactToEditDto.group), contactId);
    }

    @GetMapping("/{id}")
    public ContactToDisplayDto getById(@PathVariable(name = "id") long contactId) {

        Contact contact = contactService.getById(contactId);
        return contactMapper.toDto(contact);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") long contactId) {

        contactService.deleteById(contactId);
    }
}
