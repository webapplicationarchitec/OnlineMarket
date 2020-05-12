package miu.edu.cs545.repository;

import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepositoryJ extends JpaRepository<Product,Integer> {
    //@Query("from Auction a join a.category c where c.name=:categoryName")
//public Iterable<Auction> findByCategory(@Param("categoryName") String categoryName);
//    @Query("SELECT new miu.edu.maxrank.dto.OrderResponse(c.name , p.productName) FROM Customer c JOIN c.products p")
    @Query("FROM Product ORDER BY id desc")
    public List<Product> getTopProducts();
    @Query("from Buyer b join b.followerList f join f.listProduct where b.username=:username")
    List<Product> getFollowerProducts(@Param("username") String username);
    @Query("FROM Product p where p.category=: category_id")
    List<Product> getByCategory(@Param("category_id") Integer category_id);
}
