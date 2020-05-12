package miu.edu.cs545.repository;

import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.domain.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends CrudRepository<Seller, String> {

}
