package miu.edu.cs545.repository;

import miu.edu.cs545.domain.OnlineOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OnlineOrder, Integer> {

}
