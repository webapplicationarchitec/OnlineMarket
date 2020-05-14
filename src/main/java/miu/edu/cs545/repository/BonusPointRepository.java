package miu.edu.cs545.repository;

import miu.edu.cs545.domain.BonusPoint;
import miu.edu.cs545.domain.Buyer;
import miu.edu.cs545.domain.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BonusPointRepository extends CrudRepository<BonusPoint, Long> {

    Optional<BonusPoint> findBySellerAndBuyer(Seller seller, Buyer buyer);
}
