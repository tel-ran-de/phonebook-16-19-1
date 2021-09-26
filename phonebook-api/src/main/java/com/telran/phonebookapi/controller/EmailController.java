package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.email.EmailToAddDto;
import com.telran.phonebookapi.dto.email.EmailToDisplayDto;
import com.telran.phonebookapi.dto.email.EmailToEditDto;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.mapper.EmailMapper;
import com.telran.phonebookapi.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/email")
public class EmailController {
    private final EmailService emailService;
    private final EmailMapper emailMapper;

    public EmailController(EmailService emailService, EmailMapper emailMapper) {
        this.emailService = emailService;
        this.emailMapper = emailMapper;
    }

    @PostMapping
    public EmailToDisplayDto add(@RequestBody EmailToAddDto emailToAddDto) {
        Email email = emailService.addEmail(emailToAddDto.email, emailToAddDto.isFavorite, emailToAddDto.contactId);
        return emailMapper.toDisplayDto(email);
    }

    @PutMapping("/{id}")
    public void edit(@RequestBody EmailToEditDto toEditDto, @PathVariable(name = "id") long emailId) {
        emailService.editEmailById(toEditDto.email, toEditDto.isFavorite, emailId);
    }

    @GetMapping("/{id}")
    public EmailToDisplayDto getById(@PathVariable(name = "id") long emailId) {
        Email email = emailService.getEmailById(emailId);
        return emailMapper.toDisplayDto(email);
    }

    @GetMapping("/{id}/all")
    public List<EmailToDisplayDto> getAllByContactId(@PathVariable(name = "id") long contactId) {
        List<Email> emails = emailService.getAllEmailsByContactId(contactId);
        return emails.stream().map(emailMapper::toDisplayDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long emailId) {
        emailService.deleteEmailById(emailId);
    }
}
