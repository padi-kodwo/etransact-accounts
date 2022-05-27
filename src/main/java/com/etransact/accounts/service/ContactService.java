package com.etransact.accounts.service;


import com.etransact.accounts.dto.CreateContactDto;
import com.etransact.accounts.dto.CreateUserDto;
import com.etransact.accounts.dto.EditContactDto;
import com.etransact.accounts.dto.EditUserDto;
import com.etransact.accounts.entity.Contact;
import com.etransact.accounts.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface ContactService {

    Contact get(String id);

    Page<Contact> getAll(Specification<Contact> spec, Pageable pageable);

    Contact create(CreateContactDto createContactDto);

    Contact update(EditContactDto editContactDto);

    void delete(String id);

    long count();
}
