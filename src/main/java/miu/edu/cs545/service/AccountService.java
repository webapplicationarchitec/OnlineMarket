package miu.edu.cs545.service;

import miu.edu.cs545.domain.Account;

import java.util.List;

public interface AccountService {
    public Account create(Account account);

    public Account getByUserName(String username);

    public List<Account> getUnApprovedAccounts();
}
