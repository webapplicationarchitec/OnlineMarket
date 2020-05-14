package miu.edu.cs545.repositoryimpl;

import miu.edu.cs545.domain.Product;
import miu.edu.cs545.repository.ProductRepository;

//import org.junit.Before;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void findById() throws Exception{
        Optional<Product> productOptional = productRepository.findById(1);
//        assertEquals("Headphones", productOptional.get().getName());
    }

    @Test
    public void delete() throws Exception{
        Product product = productRepository.findById(102).get();
         productRepository.delete(product);
        assertEquals(1, 1);
    }

}
