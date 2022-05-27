package com.etransact.accounts.service.impl;

import com.etransact.accounts.dto.CreateContactDto;
import com.etransact.accounts.dto.EditContactDto;
import com.etransact.accounts.entity.Contact;
import com.etransact.accounts.entity.User;
import com.etransact.accounts.exception.ServiceException;
import com.etransact.accounts.repository.ContactRepository;
import com.etransact.accounts.service.ContactService;
import com.etransact.accounts.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private final UserService userService;

    private final  ModelMapper modelMapper;

    @Override
    public Contact get(String id) {
        return contactRepository.findById(id).orElseThrow(() ->
                new ServiceException(-1,
                        "contact not found by Id: " + id ));
    }

    @Override
    public Page<Contact> getAll(Specification<Contact> spec, Pageable pageable) {
        return contactRepository.findAll(spec, pageable);
    }

    @Override
    public Contact create(CreateContactDto createContactDto) {
        User user = userService.get(createContactDto.getUserId());
        Contact contact = modelMapper.map(createContactDto, Contact.class);
        contact.setUser(user);

        return contactRepository.save(contact);
    }

    @Override
    public Contact update(EditContactDto editContactDto) {
        // skipping this no requirement
        return null;
    }

    @Override
    public void delete(String id) {
        // skipping this no requirement

    }

    @Override
    public long count() {
        return contactRepository.count();
    }
}
