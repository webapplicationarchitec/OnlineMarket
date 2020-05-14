package miu.edu.cs545.repository;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.Buyer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepository extends AccountRepository<Buyer> {
    public Buyer findBuyerByUsername(String username);
}
