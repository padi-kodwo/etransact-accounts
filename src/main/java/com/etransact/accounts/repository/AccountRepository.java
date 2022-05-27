package com.etransact.accounts.repository;


import com.etransact.accounts.entity.Account;
import com.etransact.accounts.entity.Contact;
import com.etransact.accounts.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, String>, JpaSpecificationExecutor<Account> {
    Optional<Account> findByNumber(String username);

}