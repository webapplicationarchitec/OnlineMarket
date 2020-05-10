package miu.edu.cs545.repository;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

}
