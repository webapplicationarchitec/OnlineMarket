package miu.edu.cs545.repository;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository<T extends Account> extends CrudRepository<T, String> {

    @Query("select  p from Seller p where p.accountStatus=0")
    public List<Account> getNewSellerAccount();


    @Modifying
    @Query("update Seller s set s.accountStatus=:status where s.username=:username")
    public void approveNewSellerAccount(@Param("status") AccountStatus status, @Param("username") String username);

}

