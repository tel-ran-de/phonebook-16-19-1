package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.phonedto.PhoneToAddDto;
import com.telran.phonebookapi.dto.phonedto.PhoneToDisplayDto;
import com.telran.phonebookapi.dto.phonedto.PhoneToEditDto;
import com.telran.phonebookapi.entity.Phone;
import com.telran.phonebookapi.mapper.PhoneMapper;
import com.telran.phonebookapi.service.PhoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/phone")
public class PhoneController {
    private final PhoneService phoneService;
    private final PhoneMapper phoneMapper;

    public PhoneController(PhoneService phoneService, PhoneMapper phoneMapper) {
        this.phoneService = phoneService;
        this.phoneMapper = phoneMapper;
    }

    @PostMapping
    public PhoneToDisplayDto add(@RequestBody PhoneToAddDto phoneToAddDto) {
        Phone phone = phoneService.addPhone(
                phoneToAddDto.countryCode,
                phoneToAddDto.telephoneNumber,
                phoneToAddDto.isFavorite,
                phoneToAddDto.contactId);
        return phoneMapper.toDisplayDto(phone);
    }

    @PutMapping("/{id}")
    public void edit(@RequestBody PhoneToEditDto phoneToEditDto, @PathVariable(name = "id") long phoneId) {

        phoneService.editById(
                phoneToEditDto.countryCode,
                phoneToEditDto.telephoneNumber,
                phoneToEditDto.isFavorite,
                phoneId);
    }

    @GetMapping("/{id}")
    public PhoneToDisplayDto getById(@PathVariable(name = "id") long phoneId) {
        Phone phone = phoneService.getById(phoneId);
        return phoneMapper.toDisplayDto(phone);
    }

    @GetMapping("/{id}/all")
    public List<PhoneToDisplayDto> getAllByContactId(@PathVariable(name = "id") long contactId) {
        List<Phone> phones = phoneService.getAllByContactId(contactId);
        return phones.stream().map(phoneMapper::toDisplayDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long phoneId) {
        phoneService.deleteById(phoneId);
    }
}
