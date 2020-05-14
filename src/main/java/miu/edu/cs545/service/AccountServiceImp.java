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
    public <T extends Account> boolean createAccount(T account) {

        Optional<T> existAcc = accountRepository.findById(account.getUsername());
        if(existAcc.isPresent()){
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = account.getPassword();
        String encryptedPassword = encoder.encode(password);
        account.setPassword(encryptedPassword);

        T createdAcc = (T)accountRepository.save(account);

        return (createdAcc != null);
    }

    @Override
    public <T extends Account> T saveProfile(T account) {

        T created = (T)accountRepository.save(account);
        return created;
    }

    public <T extends Account> T getByUsername(String username) {
        Optional<T> opt = accountRepository.findById(username);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

//    public <T extends Account> List<T> listAccount() {
//        Optional<T> opt = accountRepository.findById(username);
//        if (opt.isPresent()) {
//            return opt.get();
//        }
//        return null;
//    }

//    public Account getByUsername1(String username) {
//        Optional<Account> opt = accountRepository.findById(username);
//        if (opt.isPresent()) {
//            return opt.get();
//        }
//        return null;
//    }

    @Override
    public List<Account> getNewSellerAccount() {
        return accountRepository.getNewSellerAccount();
    }

    @Override
    public void approveNewAcount(AccountStatus status, String username) {
        accountRepository.approveNewSellerAccount(status,username);
    }
}
