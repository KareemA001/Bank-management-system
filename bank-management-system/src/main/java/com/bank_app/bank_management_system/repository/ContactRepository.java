package com.bank_app.bank_management_system.repository;

import com.bank_app.bank_management_system.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {

}
