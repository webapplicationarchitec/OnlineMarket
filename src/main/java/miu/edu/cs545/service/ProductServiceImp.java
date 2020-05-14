package miu.edu.cs545.service;

import miu.edu.cs545.domain.Product;

import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.repository.OrderDetailRepository;
import miu.edu.cs545.repository.ProductPagingRepository;
import miu.edu.cs545.repository.ProductRepository;

import miu.edu.cs545.repository.ProductRepositoryJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private ProductRepositoryJ productRepositoryJ;

    private OrderDetailRepository orderDetailRepository;
    @Autowired
    public ProductServiceImp( ProductPagingRepository productPagingRepository,
     ProductRepository productRepository,ProductRepositoryJ productRepositoryJ ,OrderDetailRepository orderDetailRepository) {
      this.productRepository=productRepository;
      this.productPagingRepository=productPagingRepository;
      this.productRepositoryJ=productRepositoryJ;
      this.orderDetailRepository=orderDetailRepository;
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
        //Because there is not casecase orderdetail in product side, if we want to delete product and
        //also delete ordertail. We need to delete orderdetail firstly. If this case cenarious, the quirerement is that
        //we don't allow delete product if product has orderdetail, so we commant the code line below.
        //orderDetailRepository.deleteByProductId(product.getId());

         productRepository.delete(product);

    }

    @Override
    public List<Product> getTopProducts() {

        return toList(productRepositoryJ.findAll(Sort.by(Sort.Direction.DESC, "id")));//getTopProducts());
    }

    @Override
    public List<Product> getFollowerProducts(String username) {
        return toList(productRepositoryJ.getFollowerProducts(username));
    }

    @Override
    public List<Product> getByCategory(Integer categoryid) {
        return toList(productRepositoryJ.getByCategory( categoryid));
    }

    @Override
    public List<Product> findProductBySeller(Seller seller) {
        return toList(productRepositoryJ.findAllBySeller(seller));
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
