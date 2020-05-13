package miu.edu.cs545.repository;

import miu.edu.cs545.domain.Buyer;
import miu.edu.cs545.domain.Seller;
import org.springframework.data.repository.CrudRepository;

public interface FollowerRepository extends CrudRepository<Seller, String> {
}
