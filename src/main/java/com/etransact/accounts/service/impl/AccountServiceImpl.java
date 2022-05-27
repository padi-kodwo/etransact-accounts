package com.etransact.accounts.service.impl;

import com.etransact.accounts.dto.*;
import com.etransact.accounts.entity.Account;
import com.etransact.accounts.entity.User;
import com.etransact.accounts.exception.ServiceException;
import com.etransact.accounts.repository.AccountRepository;
import com.etransact.accounts.service.AccountService;
import com.etransact.accounts.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserService userService;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Override
    public Account get(String id) {
        return accountRepository.findById(id).orElseThrow(() ->
                new ServiceException(-1,
                        "Account not found by Id: " + id ));
    }

    @Override
    public Account getByNumber(String accountNumber) {
        return accountRepository.findByNumber(accountNumber).orElseThrow(() ->
                new ServiceException(-1,
                        "Account not found by number: " + accountNumber ));
    }

    @Override
    public Page<Account> getAll(Specification<Account> spec, Pageable pageable) {
        return accountRepository.findAll(spec, pageable);
    }

    @Override
    public Account create(CreateAccountDto createAccountDto) {

        User user = userService.get(createAccountDto.getUserId());

        // check if user has KYC

        // check if user has that type of account already

        if (!validateAccountType(user.getAccounts(), createAccountDto.getType()))
            throw new  ServiceException(-1,
                "Account has that account type already" );

        Account account = modelMapper.map(createAccountDto, Account.class);

        if(!validateAccountBalance(account))
            throw new  ServiceException(-1,
                    "Account must have at least amount 10 for current and 50 for savings" );

        account.setUser(user);

        return accountRepository.save(account);
    }

    @Override
    public Account update(EditAccountDto editAccountDto) {
        return null;
    }

    @Override
    public Account deposit(DepositDto depositDto) {

        Account account = getByNumber(depositDto.getAccountNumber());
        account.setBalance(account.getBalance().add(depositDto.getAmount()));

        //transaction call will go here

        return accountRepository.save(account);
    }

    @Override
    public Account transfer(TransferDto transferDto) {

        Account originAccount = getByNumber(transferDto.getOriginAccountNumber());

        originAccount.setBalance(originAccount.getBalance().subtract(transferDto.getAmount()));

        if (!validateAccountBalance(originAccount))
            throw new  ServiceException(-1,
                    "Transfer failed, insufficient balance" );

        Account destinationAccount = getByNumber(transferDto.getDestinationAccountNumber());
        destinationAccount.setBalance(destinationAccount.getBalance().add(transferDto.getAmount()));

        //transaction call here

        originAccount = accountRepository.save(originAccount);
        accountRepository.save(destinationAccount);


        return originAccount;
    }

    @Override
    public Account withdrawal(WithdrawalDto withdrawalDto) {

        if (withdrawalDto.getAmount().compareTo(BigDecimal.valueOf(5001))>= 0)
            throw new  ServiceException(-1,
                    "you cant withdraw above 5000 ");

        Account account = getByNumber(withdrawalDto.getAccountNumber());
        account.setBalance(account.getBalance().subtract(withdrawalDto.getAmount()));

        if (!validateAccountBalance(account))
            throw new  ServiceException(-1,
                    "Transfer failed, insufficient balance" );

        //transaction call here
        return accountRepository.save(account);
    }

    @Override
    public long count() {
        return accountRepository.count();
    }


    private boolean validateAccountType(List<Account> accounts, String currentAccountType){

        if (accounts == null)
            return true;

        if (accounts.isEmpty())
            return true;

        for (Account account : accounts) {

            if (account.getType().equals(currentAccountType))
                return false;

        }
        return true;
    }

    private boolean validateAccountBalance( Account account){

        // for saving

        if (account.getType().equals("CURRENT") && account.getBalance().compareTo(BigDecimal.valueOf(10)) >=0 )
            return true;


        if (account.getType().equals("SAVINGS") && account.getBalance().compareTo(BigDecimal.valueOf(50)) >=0 )
            return true;


        return false;
    }
}
