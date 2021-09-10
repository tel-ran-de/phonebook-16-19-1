package com.telran.phonebookapi.repo;

import com.telran.phonebookapi.entity.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IContactRepo extends CrudRepository<Contact, Long> {

    List<Contact> findAll();

}
