package com.telran.phonebookapi.repo;

import com.telran.phonebookapi.entity.Email;
import org.springframework.data.repository.CrudRepository;

public interface IEmailRepo extends CrudRepository<Email, Long> {

}
