package miu.edu.cs545.securiry;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.Admin;
import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private AccountService accountService;

    @Autowired
    public UserDetailsServiceImp(AccountService accountService) {
        this.accountService = accountService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.getByUsername(username);

        if (account != null) {
            return new UserDetailsImp(account);
        } else {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }
    }
}
