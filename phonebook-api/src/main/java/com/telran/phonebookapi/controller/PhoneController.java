package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.phonedto.PhoneToAddDto;
import com.telran.phonebookapi.dto.phonedto.PhoneToDisplayDto;
import com.telran.phonebookapi.dto.phonedto.PhoneToEditDto;
import com.telran.phonebookapi.entity.Phone;
import com.telran.phonebookapi.mapper.PhoneMapper;
import com.telran.phonebookapi.service.PhoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Phone API")
@RestController
@RequestMapping("api/phones")
public class PhoneController {
    private final PhoneService phoneService;
    private final PhoneMapper phoneMapper;

    public PhoneController(PhoneService phoneService, PhoneMapper phoneMapper) {
        this.phoneService = phoneService;
        this.phoneMapper = phoneMapper;
    }

    @ApiOperation(value = "Add a new phone to contact", tags = {"add"})
    @PostMapping
    public PhoneToDisplayDto add(@RequestBody @Valid PhoneToAddDto phoneToAddDto) {
        Phone phone = phoneService.addPhone(
                phoneToAddDto.countryCode,
                phoneToAddDto.telephoneNumber,
                phoneToAddDto.isFavorite,
                phoneToAddDto.contactId);
        return phoneMapper.toDisplayDto(phone);
    }

    @ApiOperation(value = "Update phone by id", tags = {"edit"})
    @PutMapping("/{id}")
    public void edit(@RequestBody @Valid PhoneToEditDto phoneToEditDto, @PathVariable(name = "id") long phoneId) {

        phoneService.editById(
                phoneToEditDto.countryCode,
                phoneToEditDto.telephoneNumber,
                phoneToEditDto.isFavorite,
                phoneId);
    }

    @ApiOperation(value = "Get phone by id", tags = {"get by id"})
    @GetMapping("/{id}")
    public PhoneToDisplayDto getById(@PathVariable(name = "id") long phoneId) {
        Phone phone = phoneService.getById(phoneId);
        return phoneMapper.toDisplayDto(phone);
    }

    @ApiOperation(value = "Get all phones by contact id", notes = "Status 400 if contact is not exist", tags = {"get all"})
    @GetMapping("/{id}/all")
    public List<PhoneToDisplayDto> getAllByContactId(@PathVariable(name = "id") long contactId) {
        List<Phone> phones = phoneService.getAllByContactId(contactId);
        return phones.stream().map(phoneMapper::toDisplayDto).collect(Collectors.toList());
    }

    @ApiOperation(value = "Remove phone by id", tags = {"remove"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long phoneId) {
        phoneService.deleteById(phoneId);
    }
}
