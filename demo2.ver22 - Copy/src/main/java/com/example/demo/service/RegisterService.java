package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final AccountRepository accountRepository;

    @Autowired
    public RegisterService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean accountExist(String username){
        if(accountRepository.existsAccountByUsername(username)){
            return true;
        }
        return false;
    }

    public void saveAccount(Account account) {
         accountRepository.save(account);
    }
}
