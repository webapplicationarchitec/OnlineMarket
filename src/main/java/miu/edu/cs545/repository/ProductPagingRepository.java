package miu.edu.cs545.repository;

import miu.edu.cs545.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPagingRepository extends PagingAndSortingRepository<Product,Integer>  {

}