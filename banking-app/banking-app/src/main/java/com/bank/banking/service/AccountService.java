package com.bank.banking.service;

import com.bank.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto updateAmount(Long id, double amount);

    AccountDto withDrawAmount(Long id, double amount);

    List<AccountDto> GetAllAccountDetails();

    void deleteAccount(Long id);
}
