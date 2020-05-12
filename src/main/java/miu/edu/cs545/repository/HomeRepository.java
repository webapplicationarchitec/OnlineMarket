package miu.edu.cs545.repository;

import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Review;
import miu.edu.cs545.domain.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface HomeRepository {
    List<Product> getAllProducts();
    List<Product> getTopProducts();
    List<Seller> getFollowerByBuyer(String username);
    List<Product> getFollowerProducts(List<Seller> sellerList);
    List<Product> getProductsByCategory(String category);
    List<Review> getReviewsByProduct(Integer productid);
    List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
    Product getProductById(String productId);

}
