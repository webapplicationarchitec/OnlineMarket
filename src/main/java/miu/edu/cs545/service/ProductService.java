package miu.edu.cs545.service;

import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
   public List<Product> all();
   public Optional<Product> getById(Integer id);
   public Page<Product> paging(String username ,Pageable pageable);
   public Product save(Product product);
   public void  delete(Integer id);
   List<Product> getTopProducts();
   List<Product> getFollowerProducts(String username);
   List<Product> getByCategory(Integer categoryid);
   public List<Product> findProductBySeller(Seller seller);
   public Product update(Product product);
   public List<Product> allpage();

}
