package com.etransact.accounts.service;


import com.etransact.accounts.dto.CreateUserDto;
import com.etransact.accounts.dto.EditUserDto;
import com.etransact.accounts.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface UserService {

    User get(String id);

    User getByUsername(String username);

    Page<User> getAll(Specification<User> spec, Pageable pageable);

    User save(User user);

    User create(CreateUserDto createUserDto);

    User update(EditUserDto editUserDto);

    void delete(String id);

    long count();
}
