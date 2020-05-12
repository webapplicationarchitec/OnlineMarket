package miu.edu.cs545.service;

import miu.edu.cs545.domain.Account;

import java.util.List;

public interface AccountService {

    public <T extends Account> T createAccount(T account);

    public <T extends Account> T getByUsername(String username);
    public Account getByUsername1(String username);
}
