package miu.edu.cs545.service;

import jdk.nashorn.internal.runtime.options.Option;
import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.domain.Product;
import miu.edu.cs545.repository.AccountRepository;
import miu.edu.cs545.repository.ProductPagingRepository;
import miu.edu.cs545.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPagingRepository productPagingRepository;


    @Override
    public Iterable<Product> all() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> paging(Pageable pageable) {
        return productPagingRepository.findAll(pageable);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
