package miu.edu.cs545.repository;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPagingRepository extends PagingAndSortingRepository<OnlineOrder,Integer>  {

}