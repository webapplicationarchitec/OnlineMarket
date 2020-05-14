package miu.edu.cs545.service;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;

import java.util.List;

public interface AccountService {

    public <T extends Account> boolean createAccount(T account);
    public <T extends Account> T saveProfile(T account);

    public List<Account> getNewSellerAccount();

    public void approveNewAcount(AccountStatus status, String username);

    public <T extends Account> T getByUsername(String username);
//    public Account getByUsername1(String username);
}
