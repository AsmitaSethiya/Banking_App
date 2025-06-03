package com.bank.banking.service.impl;

import com.bank.banking.dto.AccountDto;
import com.bank.banking.entity.Account;
import com.bank.banking.exception.AccountException;
import com.bank.banking.mapper.AccountMapper;
import com.bank.banking.repository.AccountRepository;
import com.bank.banking.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImp implements AccountService {

   private  AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccount(accountDto);

        Account saveAccount =  accountRepository.save(account);


        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {

        Account account =  accountRepository
                .findById(id)
                .orElseThrow(()-> new AccountException("Account does not existing ."));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto updateAmount(Long id, double amount) {
        Account account =  accountRepository
                .findById(id)
                .orElseThrow(()-> new AccountException("Account does not existing ."));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account saveAccount =  accountRepository.save(account);
        return  AccountMapper.mapToAccountDto(saveAccount);


    }

    @Override
    public AccountDto withDrawAmount(Long id, double amount) {
        Account account =  accountRepository
                .findById(id)
                .orElseThrow(()-> new AccountException("Account does not existing ."));
        if(account.getBalance() < amount)
        {
            throw new RuntimeException("Insufficient amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account saveAccount  = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public List<AccountDto> GetAllAccountDetails() {
       List<Account>  accounts = accountRepository.findAll();
      return accounts.stream().map((account)->
               AccountMapper.mapToAccountDto(account))
               .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account =  accountRepository
                .findById(id)
                .orElseThrow(()-> new AccountException("Account does not existing ."));
        accountRepository.deleteById(id);
    }
}
