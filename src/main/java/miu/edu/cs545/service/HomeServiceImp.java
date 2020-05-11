package miu.edu.cs545.service;

import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.repository.HomeRepository;
import miu.edu.cs545.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return null;
    }

    @Override
    public List<Product> getFollowerProducts(List<Seller> sellerList) {
        return null;
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




}
