package com.bank.banking.controller;

import com.bank.banking.dto.AccountDto;
import com.bank.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //ADD ACCOUNT REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //get account rest api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
    {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //update Amount REST API
    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto> deposite( @PathVariable Long id,@RequestBody Map<String, Double> request)
    {
        Double amount = request.get("amount");
        AccountDto accountDto =   accountService.updateAmount(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //withdraw amount rest api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String, Double>  request)
    {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withDrawAmount(id,amount);
        return  ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public  ResponseEntity<List<AccountDto>> getAllAccounts()
    {
        List<AccountDto> accounts =  accountService.GetAllAccountDetails();
        return ResponseEntity.ok(accounts);
    }

    //DELETE ACCOUNT REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> dataAccount(@PathVariable Long id)
    {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully");
    }

}
