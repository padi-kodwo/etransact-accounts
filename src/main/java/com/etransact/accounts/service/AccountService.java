package com.etransact.accounts.service;


import com.etransact.accounts.dto.*;
import com.etransact.accounts.entity.Account;
import com.etransact.accounts.entity.Contact;
import com.etransact.accounts.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface AccountService {

    Account get(String id);

    Account getByNumber(String accountNumber);

    Page<Account> getAll(Specification<Account> spec, Pageable pageable);

    Account create(CreateAccountDto createAccountDto);

    Account update(EditAccountDto editAccountDto);

    Account deposit(DepositDto depositDto);

    Account transfer(TransferDto transferDto);

    Account withdrawal(WithdrawalDto withdrawalDto);

    long count();
}
