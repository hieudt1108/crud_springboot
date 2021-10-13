package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleService roleService;

    @Autowired
    public AccountService(AccountRepository accountRepository, RoleService roleService) {
        this.accountRepository = accountRepository;
        this.roleService = roleService;
    }
    

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountRepository.getAccountByUsername(s);
        if (account == null || account.isEnable()==false) {
            throw new UsernameNotFoundException("User not found");
        }
        List<Role> listR = roleService.getAllRolesById(account.getId());
        Set<String> listS = new HashSet<>();
        for (Role role : listR) {
            listS.add(role.getRole());
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String string : listS) {
            grantedAuthorities.add(new SimpleGrantedAuthority(string.trim()));
        }
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(), account.getPassword(), grantedAuthorities);
    }



}
