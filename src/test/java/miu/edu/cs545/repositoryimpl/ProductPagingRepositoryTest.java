package miu.edu.cs545.repositoryimpl;

import miu.edu.cs545.domain.Product;
import miu.edu.cs545.repository.ProductPagingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//import static org.junit.Assert.assertEquals;


@DataJpaTest
public class ProductPagingRepositoryTest {
    @Autowired
    ProductPagingRepository productPagingRepository;

    @Test
    public void findAll() throws Exception{

        List<String> properties = Arrays.asList("name");

        Pageable sortedByName =
                PageRequest.of(0, 3, Sort.by("name"));

       // Pageable sortedByPriceDesc =
         //       PageRequest.of(0, 3, Sort.by("price").descending());

        //Pageable sortedByPriceDescNameAsc =
         //       PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));

        Page<Product> productOptional = productPagingRepository.findAll(sortedByName);

        //assertEquals(3,  productOptional.getSize());

//        assertEquals(8,  productOptional.getTotalElements());
    }
}
