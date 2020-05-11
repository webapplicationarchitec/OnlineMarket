package miu.edu.cs545.repository;

import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Seller;

import java.util.List;
import java.util.Map;

public interface HomeRepository {
    List<Product> getAllProducts();
    List<Product> getTopProducts();
    List<Product> getFollowerProducts(List<Seller> sellerList);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
    Product getProductById(String productId);

}
