package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Product;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final AccountRepository accountRepository;

    @Autowired
    public UserService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Account getAccountById(int id){
        return accountRepository.getById(id);
    }

    public Account getAccountByName(String name ){
        return accountRepository.getAccountByUsername(name);
    }
    public void saveAccount(Account account){
        accountRepository.save(account);
    }
}
