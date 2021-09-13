package com.telran.phonebookapi.repo;

import com.telran.phonebookapi.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepo extends CrudRepository<Address, Long> {


}
