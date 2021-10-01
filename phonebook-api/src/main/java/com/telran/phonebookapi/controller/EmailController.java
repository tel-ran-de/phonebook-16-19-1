package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.email.EmailToAddDto;
import com.telran.phonebookapi.dto.email.EmailToDisplayDto;
import com.telran.phonebookapi.dto.email.EmailToEditDto;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.mapper.EmailMapper;
import com.telran.phonebookapi.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Email API")
@RestController
@RequestMapping("api/emails")
public class EmailController {
    private final EmailService emailService;
    private final EmailMapper emailMapper;

    public EmailController(EmailService emailService, EmailMapper emailMapper) {
        this.emailService = emailService;
        this.emailMapper = emailMapper;
    }

    @ApiOperation(value = "Add a new email to contact", tags = {"add"})
    @PostMapping
    public EmailToDisplayDto add(@RequestBody @Valid EmailToAddDto emailToAddDto) {
        Email email = emailService.addEmail(emailToAddDto.email, emailToAddDto.isFavorite, emailToAddDto.contactId);
        return emailMapper.toDisplayDto(email);
    }

    @ApiOperation(value = "Update email by id", tags = {"edit"})
    @PutMapping("/{id}")
    public void edit(@RequestBody @Valid EmailToEditDto toEditDto, @PathVariable(name = "id") long emailId) {
        emailService.editEmailById(toEditDto.email, toEditDto.isFavorite, emailId);
    }

    @ApiOperation(value = "Get email by id", tags = {"get by id"})
    @GetMapping("/{id}")
    public EmailToDisplayDto getById(@PathVariable(name = "id") long emailId) {
        Email email = emailService.getEmailById(emailId);
        return emailMapper.toDisplayDto(email);
    }

    @ApiOperation(value = "Get all emails by contact id", notes = "Status 400 if contact not exist", tags = {"get all"})
    @GetMapping("/{id}/all")
    public List<EmailToDisplayDto> getAllByContactId(@PathVariable(name = "id") long contactId) {
        List<Email> emails = emailService.getAllEmailsByContactId(contactId);
        return emails.stream().map(emailMapper::toDisplayDto).collect(Collectors.toList());
    }

    @ApiOperation(value = "Remove email by id", tags = {"remove"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long emailId) {
        emailService.deleteEmailById(emailId);
    }
}
