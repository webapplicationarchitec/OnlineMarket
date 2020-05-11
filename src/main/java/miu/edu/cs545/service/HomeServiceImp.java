package miu.edu.cs545.service;

import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.repository.HomeRepository;
import miu.edu.cs545.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HomeServiceImp implements HomeService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HomeRepository homeRepository;
    @Override
    public List<Product> getAllProducts() {
        return homeRepository.getAllProducts();
    }

    @Override
    public List<Product> getTopProducts() {
        return toList(homeRepository.getTopProducts());
    }

    @Override
    public List<Seller> getFollowerByBuyer(String username) {
        return toList(homeRepository.getFollowerByBuyer(username));
    }

    @Override
    public List<Product> getFollowerProducts(List<Seller> sellerList) {

        return toList(homeRepository.getFollowerProducts(sellerList));
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return homeRepository.getProductsByCategory(category);
    }

    @Override
    public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        return homeRepository.getProductsByFilter(filterParams);
    }

    @Override
    public Product getProductById(String productId) {
        return homeRepository.getProductById(productId);
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }



}
