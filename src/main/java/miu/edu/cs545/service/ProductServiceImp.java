package miu.edu.cs545.service;

import miu.edu.cs545.domain.Product;

import miu.edu.cs545.repository.ProductPagingRepository;
import miu.edu.cs545.repository.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;

    private ProductPagingRepository productPagingRepository;

    public ProductServiceImp( ProductPagingRepository productPagingRepository,
     ProductRepository productRepository) {
      this.productRepository=productRepository;
      this.productPagingRepository=productPagingRepository;
    }



    @Override
    public List<Product> all() {
        return toList(productRepository.findAll());
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

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
