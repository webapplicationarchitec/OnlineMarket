package miu.edu.cs545.service;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;

import java.util.List;

public interface AccountService {

    public Account createAccount(Account account);

    public Account getByUsername(String username);

    public List<Account> getNewSellerAccount();

    public void approveNewAcount(AccountStatus status, String username);

}
