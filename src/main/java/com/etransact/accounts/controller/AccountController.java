package com.etransact.accounts.controller;


import com.etransact.accounts.dto.*;
import com.etransact.accounts.dto.response.ApiResponse;
import com.etransact.accounts.dto.response.PagedContent;
import com.etransact.accounts.entity.Account;
import com.etransact.accounts.entity.User;
import com.etransact.accounts.service.AccountService;
import com.etransact.accounts.spec.ListUserSpec;
import com.etransact.accounts.spec.SearchUAccountSpec;
import com.etransact.accounts.util.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    @GetMapping("/search")
    public ApiResponse<PagedContent<AccountDto>> searchAccounts(SearchUAccountSpec searchUserSpec,
                                                                Pageable pageable,
                                                                HttpServletRequest httpServletRequest){
        String sessionId = httpServletRequest.getSession().getId();

        logger.info("["+ sessionId +"] http request: searchAccounts");

        Page<Account> all = accountService.getAll(searchUserSpec, pageable);
        List<AccountDto> accountDtos = all
                .stream()
                .map(user -> modelMapper.map(user, AccountDto.class))
                .collect(Collectors.toList());
        ApiResponse<PagedContent<AccountDto>> apiResponse = Utils.wrapInPagedApiResponse(all, accountDtos, sessionId);

        logger.info("["+ sessionId +"] http response: searchAccounts : {}", apiResponse);

        return apiResponse;
    }

    @GetMapping(value = "/number/{number}")
    public ApiResponse<AccountDto> getAccount(@PathVariable String number,
                                                  HttpServletRequest httpServletRequest) {

        String sessionId = httpServletRequest.getSession().getId();

        logger.info("["+ sessionId +"] http request: getAccount: ", number);

        Account account = accountService.getByNumber(number);
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        ApiResponse<AccountDto> apiResponse= Utils.wrapInApiResponse(accountDto, sessionId);

        logger.info("["+ sessionId +"] http response: getAccount: {}", apiResponse);

        return apiResponse;
    }

    @PutMapping("/deposit")
    public ApiResponse<AccountDto> deposit(@RequestBody @Valid DepositDto depositDto,
                                                 HttpServletRequest httpServletRequest) {

        String sessionId = httpServletRequest.getSession().getId();

        logger.info("["+ sessionId +"] http request: deposit");

        Account domainAccount = accountService.deposit(depositDto);
        AccountDto domainAccountDto = modelMapper.map(domainAccount, AccountDto.class);
        ApiResponse<AccountDto> apiResponse= Utils.wrapInApiResponse(domainAccountDto, sessionId);

        logger.info("["+ sessionId +"] http response: deposit: {}", apiResponse);

        return apiResponse;
    }


    @PutMapping("/transfer")
    public ApiResponse<AccountDto> transfer(@RequestBody @Valid TransferDto transferDto,
                                           HttpServletRequest httpServletRequest) {

        String sessionId = httpServletRequest.getSession().getId();

        logger.info("["+ sessionId +"] http request: transfer");

        Account domainAccount = accountService.transfer(transferDto);
        AccountDto domainAccountDto = modelMapper.map(domainAccount, AccountDto.class);
        ApiResponse<AccountDto> apiResponse= Utils.wrapInApiResponse(domainAccountDto, sessionId);

        logger.info("["+ sessionId +"] http response: transfer: {}", apiResponse);

        return apiResponse;
    }


    @PutMapping("/withdraw")
    public ApiResponse<AccountDto> withdrawal(@RequestBody @Valid WithdrawalDto withdrawalDto,
                                              HttpServletRequest httpServletRequest) {

        String sessionId = httpServletRequest.getSession().getId();

        logger.info("["+ sessionId +"] http request: withdrawal");

        Account domainAccount = accountService.withdrawal(withdrawalDto);
        AccountDto domainAccountDto = modelMapper.map(domainAccount, AccountDto.class);
        ApiResponse<AccountDto> apiResponse= Utils.wrapInApiResponse(domainAccountDto, sessionId);

        logger.info("["+ sessionId +"] http response: withdrawal: {}", apiResponse);

        return apiResponse;
    }
}
