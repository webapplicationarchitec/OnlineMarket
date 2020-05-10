package miu.edu.cs545.service;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Account getByUsername(String username) {
        Optional<Account> opt = accountRepository.findById(username);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }
}
