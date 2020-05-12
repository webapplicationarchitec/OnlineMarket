package miu.edu.cs545.service;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImp implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = account.getPassword();
        String encryptedPassword = encoder.encode(password);
        account.setPassword(encryptedPassword);

        Account created = accountRepository.save(account);
        return created;
    }

    public Account getByUsername(String username) {
        Optional<Account> opt = accountRepository.findById(username);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public List<Account> getNewSellerAccount() {
        return accountRepository.getNewSellerAccount();
    }

    @Override
    public void approveNewAcount(AccountStatus status, String username) {
        accountRepository.approveNewSellerAccount(status,username);
    }
}
