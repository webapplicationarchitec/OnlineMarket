package miu.edu.cs545.repositoryimpl;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.domain.Product;
import miu.edu.cs545.repository.AccountRepository;
import miu.edu.cs545.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void findNewSellerAccount() throws Exception{
        List<Account> accounts = accountRepository.getNewSellerAccount();
        assertEquals(2, accounts.size());
    }

    @Test
    public void approveNewSellerAccount() throws Exception{
        accountRepository.approveNewSellerAccount(AccountStatus.Rejected,"luannguyen");
        assertEquals(2, 2);
    }



}
