package miu.edu.cs545.service;

import miu.edu.cs545.domain.Category;
import miu.edu.cs545.domain.Product;

import miu.edu.cs545.domain.ProductStatus;
import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.repository.OrderDetailRepository;
import miu.edu.cs545.repository.ProductPagingRepository;
import miu.edu.cs545.repository.ProductRepository;

import miu.edu.cs545.repository.ProductRepositoryJ;
import miu.edu.cs545.utility.BlobAzure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;

    private ProductPagingRepository productPagingRepository;

    private ProductRepositoryJ productRepositoryJ;

    private OrderDetailRepository orderDetailRepository;

    private AccountService accountService;

    private CategoryService  categoryService;
    @Autowired
    public ProductServiceImp( ProductPagingRepository productPagingRepository
            ,ProductRepository productRepository
            ,ProductRepositoryJ productRepositoryJ
            ,OrderDetailRepository orderDetailRepository
            ,AccountService accountService
            ,CategoryService  categoryService)
    {
      this.productRepository=productRepository;
      this.productPagingRepository=productPagingRepository;
      this.productRepositoryJ=productRepositoryJ;
      this.orderDetailRepository=orderDetailRepository;
      this.accountService=accountService;
      this.categoryService=categoryService;
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
    public Page<Product> paging(String username ,Pageable pageable) {
        Seller seller=new Seller();
        seller.setUsername(username);
        return productPagingRepository.findProductsBySeller(seller,pageable);
    }

    @Override
    public Product save(Product product) {

        //Upload File to azure blobAzure
        BlobAzure blobAzure=new BlobAzure();
        if(!product.getImage().isEmpty()) {
            String path = blobAzure.Upload(product);
            product.setPhoto(path);
        }

        product.setStatus(ProductStatus.New);

        product.setCreatedDate(new Date());

        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        //Because there is not casecase orderdetail in product side, if we want to delete product and
        //also delete ordertail. We need to delete orderdetail firstly. If this case cenarious, the quirerement is that
        //we don't allow delete product if product has orderdetail, so we commant the code line below.
        //orderDetailRepository.deleteByProductId(product.getId());

       //  productRepository.delete(product);
        productRepository.deleteProductAndDeleteOrderDetailWihoutCastcase(id);

    }

    @Override
    public List<Product> getTopProducts() {

        return toList(productRepositoryJ.findAll(Sort.by(Sort.Direction.DESC, "createdDate")));//getTopProducts());
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

    @Override
    public Product update(Product product) {
        return productRepositoryJ.save(product);
    }

    @Override
    public List<Product> allpage() {
        return toList(productRepositoryJ.findAll());
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
