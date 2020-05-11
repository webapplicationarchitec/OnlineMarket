package miu.edu.cs545.repositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepository {
    @Autowired
    ProductRepository productRepository;
}
