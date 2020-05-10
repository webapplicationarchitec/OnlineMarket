package miu.edu.cs545.securiry;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImp implements UserDetails {

    private final Account account;

    public UserDetailsImp(Account account) {
        this.account = account;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
//        Integer type = account.getType();
//        String roleName = type == 0 ? "ADMIN" : (type == 1 ? "SELLER" : "BUYER");
//        roles.add(new SimpleGrantedAuthority(roleName));
        return roles;
    }

    public String getPassword() {
        return account.getPassword();
    }

    public String getUsername() {
        return account.getUsername();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return account.getAccountStatus() == AccountStatus.Approved;
    }
}
