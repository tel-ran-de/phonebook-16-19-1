package com.telran.phonebookapi.repo;

import com.telran.phonebookapi.entity.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPhoneRepo extends CrudRepository<Phone, Long> {

}
