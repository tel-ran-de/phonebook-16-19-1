package com.telran.phonebookapi.repo;

import com.telran.phonebookapi.entity.Group;
import org.springframework.data.repository.CrudRepository;

public interface IGroupRepo extends CrudRepository<Group, Long> {
}
