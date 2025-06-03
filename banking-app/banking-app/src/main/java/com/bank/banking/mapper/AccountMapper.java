package com.bank.banking.mapper;

import com.bank.banking.dto.AccountDto;
import com.bank.banking.entity.Account;

public class AccountMapper {

    //account dto to jpa entity
    public static Account mapToAccount(AccountDto accountDto)
    {

        Account account = new Account(

           accountDto.getId(),
             accountDto.getAccountHolderName(),
             accountDto.getBalance()
        );

        return account;
    }

    //jpa entity to account dto

    public static AccountDto mapToAccountDto(Account account)
    {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );

        return  accountDto;
    }
}
