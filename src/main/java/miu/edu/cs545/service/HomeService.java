package miu.edu.cs545.service;

import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Seller;

import java.util.List;
import java.util.Map;

public interface HomeService {

    List<Product> getAllProducts();
    List<Product> getTopProducts();
    List<Seller> getFollowerByBuyer(String username);
    List<Product> getFollowerProducts(List<Seller> sellerList);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
    Product getProductById(String productId);


}
