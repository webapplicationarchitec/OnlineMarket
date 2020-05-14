package miu.edu.cs545.repository;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPagingRepository extends PagingAndSortingRepository<OnlineOrder,Integer>  {
    public Page<OnlineOrder> findOnlineOrdersBySeller(Seller seller, Pageable pageable);
}