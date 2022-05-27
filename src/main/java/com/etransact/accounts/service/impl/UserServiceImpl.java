package com.etransact.accounts.service.impl;

import com.etransact.accounts.dto.CreateUserDto;
import com.etransact.accounts.dto.EditUserDto;
import com.etransact.accounts.entity.User;
import com.etransact.accounts.exception.ServiceException;
import com.etransact.accounts.repository.UserRepository;
import com.etransact.accounts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public User get(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ServiceException(-1,
                        "user not found by Id: " + id ));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new ServiceException(-1,
                        "user not found by username: " + username ));
    }

    @Override
    public Page<User> getAll(Specification<User> spec, Pageable pageable) {
        return userRepository.findAll(spec, pageable);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User create(CreateUserDto createUserDto) {
        User user = modelMapper.map(createUserDto, User.class);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User update(EditUserDto editUserDto) {

        //no requirement for this yet
        return null;
    }

    @Override
    public void delete(String id) {
        // no requirement
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
