package miu.edu.cs545.service;

import jdk.nashorn.internal.runtime.options.Option;
import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
   public Iterable<Product> all();
   public Optional<Product> getById(Integer id);
   public Page<Product> paging(Pageable pageable);
   public Product save(Product product);
   public void  delete(Product product);

}
